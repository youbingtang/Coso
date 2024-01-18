<template>
    <div class="page67">
        <h1>账号管理</h1>
        <el-card class="box-card">
            <el-table
                    :data="tableData.list"
                    :header-cell-style="{textAlign:'center'}"
                    :cell-style="{ textAlign: 'center' }"
                    style="width: 70%;margin-left: 13% "
                    selection-change="handleSelectionChange"
            >
                <el-table-column prop="name" label="用户姓名" width="140">
                </el-table-column>
                <el-table-column prop="hiredDate" label="入职时间" width="140">
                </el-table-column>
                <el-table-column prop="account" label="手机号" width="140">
                </el-table-column>
                <el-table-column prop="idCardNo" label="身份证号码" width="280">
                </el-table-column>
                <el-table-column prop="roid" label="角色类型" width="140">
                </el-table-column>
                <el-table-column width="260"  label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-check" @click="start(scope.row,scope.$index)"v-show="scope.row.status===0">禁用
                        </el-button>
                        <el-button type="info" icon="el-icon-close" @click="start(scope.row,scope.$index)"v-show="scope.row.status===1">启用
                        </el-button>
                        <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-row :gutter="20">
                <el-col :span="7" :offset="12">
                    <div class="block " style="margin-left: -600px;margin-top: 20px">
                        <table style="padding-left: 30%;padding-top: 30px">
                            <tr>
                                <td>共有{{tableData.total}}条数据，</td>
                                <td>每页显示{{tableData.pageSize}}条数据,</td>
                                <td><el-button type="primary" icon="el-icon-arrow-left" style="float: left" @click="sub()">上一页</el-button></td>
                                <td>第{{tableData.pageNum}}页</td>
                                <td><el-button type="primary" style="float: right" @click="add()">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button></td>
                            </tr>
                        </table>
                    </div>
                </el-col>
            </el-row>
            <el-button style="width:10%;margin-left:-900px" type="primary" @click="toAmend()">+建立新用户</el-button>
        </el-card>

        <el-dialog title="添加账户" :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <div class="ios"style="margin-right: 200px">
                <el-form :model="ruleForm2":rules="rules2"  status-icon  style="margin-left: 270px"
                         ref="ruleForm2" label-width="100px" class="demo-addAccount" label-position="left" >
                    <el-form-item label="手机号码 " prop="account">
                        <el-input v-model="ruleForm2.account"></el-input>
                    </el-form-item>
                    <el-form-item label="用户姓名" prop="username">
                        <el-input v-model="ruleForm2.username"></el-input>
                    </el-form-item>
                    <el-form-item label="入职时间" prop="hiredDate">
                        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm2.hiredDate" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="身份证号" prop="idCardNo">
                        <el-input v-model="ruleForm2.idCardNo"></el-input>
                    </el-form-item>
                    <el-form-item label="角 色" prop="roll">
                        <el-select v-model="ruleForm2.roll" placeholder="请选择"  style="width: 350px">
                            <el-option
                                    v-for="item in ruleForm2.options1"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="ret()">返  回</el-button>
                <el-button type="primary" @click="hand('ruleForm2')">保  存</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                var reg = /^1(3|4|5|6|7|8|9)\d{9}$/
                if (!value) {
                    callback(new Error("请输入手机号"));
                } else if (!reg.test(value)) {
                    callback(new Error("手机号格式错误"));
                } else if (value.toString().length < 11 || value.toString().length > 11) {
                    callback(new Error("手机号长度为11位"));
                } else {
                    callback();
                }
            };
            var validatePass1 = (rule, value, callback) => {
                var req = /^[\u4e00-\u9fa5]+$/;
                if (!value) {
                    return callback(new Error("请输入姓名"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是中文"));
                } else if (value.toString().length > 10) {
                    return callback(new Error("长度为小于10位"));
                } else {
                    return callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error("请输入时间"));
                } else {
                    return callback();
                }
            };
            var validatePass4 = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error("请选择"));
                } else {
                    return callback();
                }
            };
            var validatePass3 = (rule, value, callback) => {
                var Errors=new Array(
                    "验证通过!",
                    "身份证号码位数不对!",
                    "身份证号码出生日期超出范围或含有非法字符!",
                    "身份证号码校验错误!",
                    "身份证地区非法!"
                );

                var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
                var idcard=value,Y,JYM;
                var S,M;
                var idcard_array = new Array();
                idcard_array = idcard.split("");
                let ereg
                if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
                    ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
                } else {
                    ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
                }

                S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                    + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                    + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                    + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                    + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                    + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                    + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                    + parseInt(idcard_array[7]) * 1
                    + parseInt(idcard_array[8]) * 6
                    + parseInt(idcard_array[9]) * 3 ;
                Y = S % 11;
                M = "F";
                JYM = "10X98765432";
                M = JYM.substr(Y,1);//判断校验位

                //地区检验
                if(idcard.length!=18){
                    callback(new Error(Errors[1]))
                }else if(area[parseInt(idcard.substr(0,2))]==null)
                {
                    callback(new Error(Errors[4]));
                }else if(!ereg.test(idcard)){
                    return callback(new Error(Errors[2]));
                }else if(M !== idcard_array[17]){
                    return callback(new Error(Errors[3]));
                }else {
                    console.log(value);
                    return callback()
                }

            };
            return {
                url: "http://localhost:8888/Cosoproject/",
                tableData:{}, //存放从后端传来的数据
                currentPage:1, //当前页数
                dialogFormVisible:false,
                ruleForm2:{
                    account:'',
                    username:'',
                    hiredDate:'',
                    idCardNo:'',
                    options1:[],
                    roll:''
                },
                rules2:{
                    account: [
                        { required: true,validator:validatePass , trigger: 'blur' }
                    ],
                    username: [
                        {required: true, validator: validatePass1, trigger: 'blur' }
                    ],
                    hiredDate: [
                        {required: true, validator: validatePass2, trigger: 'blur' }
                    ],
                    idCardNo: [
                        { required: true,validator: validatePass3, trigger: 'blur' }
                    ],
                    roll: [
                        { required: true,validator: validatePass4, trigger: 'blur' }
                    ]
                }
            }
        },
        created(){
            this.onload();
        },
        methods:{
            onload() {
                let _this = this;
                axios.get(this.url + 'findAllAccount', {
                    params: {
                        cid: sessionStorage.getItem("companyid"),
                        currentPage: this.currentPage
                    }
                }).then(function (data) {
                    _this.tableData = data.data;
                })

            },
            sub() {
                if (this.tableData.hasPreviousPage) {
                    this.currentPage--;
                    this.onload();
                }
            },
            add() {
                if (this.tableData.hasNextPage) {
                    this.currentPage++;
                    this.onload();
                }
            },
            //操作
            start(row, index) {
                let _this = this;
                axios.get(this.url + 'updateAccountStatus', {
                    params: {
                        id: row.id
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.onload();
                    }
                })
            },
            //删除
            rebut(){
                let _this = this;
                axios.get(this.url + 'delAccount', {
                    params: {
                        id: row.id
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.onload();
                    }else{
                        _this.$message({
                            message: '删除出现异常，请联系管理员',
                            type: 'error'
                        });
                    }
                })
            },
            //添加
            toAmend(){
                let  _this=this;
                this.dialogFormVisible=true;
                axios.get(this.url+'findAllRole',{
                    params:{
                        cid:sessionStorage.getItem('companyid'),
                        type:'2',
                    }
                }).then(function (data) {
                    _this.ruleForm2.options1=data.data.list;
                })
            },
            //添加里的返回
            ret(){
                this.dialogFormVisible=false;
            },
            hand(ruleForm2){
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get(this.url+'addAccount', {
                            params: {
                                cid:sessionStorage.getItem('companyid'),
                                name:this.ruleForm2.username,
                                account: this.ruleForm2.account,
                                hireDate: this.ruleForm2.hiredDate,
                                idCardNo: this.ruleForm2.idCardNo,
                                roleId:this.ruleForm2.roll,
                            }
                        }).then(function (data) {
                            if(data.data===1){
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                _this.onload();
                                _this.dialogFormVisible=false;
                            }else if(data.data===3){
                                _this.$message({
                                    message: '身份证号已存在',
                                    type: 'error'
                                });
                            }else if(data.data===2){
                                _this.$message({
                                    message: '账号已存在',
                                    type: 'error'
                                });
                            }else{
                                _this.$message({
                                    message: '发现未知异常，请联系管理员',
                                    type: 'error'
                                });
                            }

                        });
                    } else {
                        return false;
                    }
                });
            }
        }
    }
</script>
<style scoped>
    .page67{
        width: 100%;
        height: 800px;
        background-size: 100% 100%;
        overflow: hidden;
    }
    h1{
        margin-left: -1400px;
        font-size: 40px;
    }
    .box-card{
        background-color: transparent !important;
    }

</style>