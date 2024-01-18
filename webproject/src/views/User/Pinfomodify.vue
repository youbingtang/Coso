<template>
    <div class="xiugai">
        <div class="login-wrap">
            <el-form
                    :model="resetForm"
                    :rules="resetFormRules"
                    ref="resetForm"
                    status-icon
                    label-width="150px"
            >
                <h1 >基本资料
                    <el-button style="width:10%;margin-left:650px" type="primary" @click="queren('resetForm')" :disabled="resetshow===1">确认</el-button>
                    <el-button style="width:10%;margin-left:50px" type="primary" @click="reset()">修改</el-button>
                </h1>
            </el-form>
        </div>
        <div class="login-wrap2">
            <el-form
                    :model="resetForm"
                    :rules="resetFormRules"
                    ref="resetForm"
                    status-icon
                    label-width="150px"
            >
                <el-form-item label="账号：" prop="account" >
                    <el-input  type="text" v-model="resetForm.account" auto-complete="off" style="width: 60%" :disabled="resetshow===1"></el-input>
                </el-form-item>
                <el-form-item label="姓名：" prop="name">
                    <el-input type="text" v-model="resetForm.name" auto-complete="off"  style="width: 60%" :disabled="resetshow===1"></el-input>
                </el-form-item>
                <el-form-item label="身份证号码：" prop="idCardNo">
                    <el-input type="text" v-model="resetForm.idCardNo" auto-complete="off"  style="width: 60%" :disabled="resetshow===1"></el-input>
                </el-form-item>
                <el-form-item label="入职时间：" prop="hiredDate" >
                    <el-input  type="date" v-model="resetForm.hiredDate" auto-complete="off" style="width: 60%" :disabled="true"></el-input>
                </el-form-item>
                <el-button style="width:20%;margin-left:200px" type="primary" @click.native.prevent="toAmend()">修改密码</el-button>
            </el-form>
        </div>

        <el-dialog
                title="修改密码"
                :visible.sync="dialogVisible"
                width="30%">
            <span>
                <el-form
                        :model="resetForm1"
                        ref="resetForm1"
                        :rules="resetFormRules"
                        status-icon
                        label-width="150px"
                        :close-on-click-modal="false"
                        :close-on-press-escape="false"
                        :show-close="false"
                >
                    <el-form-item label="旧密码：" prop="oldpassword" >
                        <el-input  type="text" v-model="resetForm1.oldpassword" auto-complete="off" style="width: 60%" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="新密码：" prop="newpassword">
                        <el-input  type="text" v-model="resetForm1.newpassword" auto-complete="off" style="width: 60%" show-password></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码：" prop="repassword">
                        <el-input  type="text" v-model="resetForm1.repassword" auto-complete="off" style="width: 60%" show-password></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancel()">取 消</el-button>
                <el-button type="primary" @click="resetpassword('resetForm1')">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog
                title="提示"
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false"
                :visible.sync="dialogmessage"
                width="30%"
                @close="dialogmessage=false">
            <span>修改密码成功，请重新登录</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="backlogin()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Pinfomodify",
        created(){
            this.onload();
        },
        data() {
            let validatePass0 = (rule, value, callback) => {
                var reg=/^1(3|4|5|6|7|8|9)\d{9}$/
                if (!value) {
                    callback(new Error("请输入账号"));
                } else if ( !reg.test(value)){
                    callback(new Error("格式错误"));
                } else if ( value.toString().length<11||value.toString().length>11) {
                    callback(new Error("账号长度为11位"));
                } else {
                    callback();
                }
            };
            let validatePass = (rule, value, callback) => {
                var req=/^[\u4e00-\u9fa5]+$/
                if (!value) {
                    callback(new Error("请输入姓名"));
                } else if (  !req.test(value)) {
                    callback(new Error("只能是中文"));
                } else if ( value.toString().length > 8) {
                    callback(new Error("长度为小于8位"));
                }else {
                    callback();
                }
            };
            let oldpassword=(rule,value,callback)=>{
                if (!value) {
                    callback(new Error("请输入原有密码"));
                }else {
                    callback();
                }
            };
            let newpassword=(rule,value,callback)=>{
                if (!value) {
                    callback(new Error("请输入新密码"));
                }else {
                    callback();
                }
            };
            let repassword=(rule,value,callback)=>{
                if (!value) {
                    callback(new Error("请输确认密码"));
                }else if (value !== this.resetForm1.newpassword) {
                    callback(new Error("确认密码与新密码不一致"))
                } else {
                    callback();
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
                let ereg;
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
                resetshow:1,
                dialogmessage:false,
                dialogVisible:false,
                url:'http://localhost:8888/Cosoproject/',
                resetForm: {},
                resetForm1: {
                    oldpassword:'',
                    newpassword:'',
                    repassword:'',
                },
                resetFormRules: {
                    account: [
                        { required: true, validator: validatePass0, trigger: 'blur' }
                    ],
                    name: [
                        { required: true, validator: validatePass, trigger: 'blur' }
                    ],
                    oldpassword: [
                        { required: true, validator: oldpassword, trigger: 'blur' }
                    ],
                    newpassword: [
                        { required: true, validator: newpassword, trigger: 'blur' }
                    ],
                    repassword: [
                        { required: true, validator: repassword, trigger: 'blur' }
                    ],
                    idCardNo: [
                        { required: true, validator: validatePass3, trigger: 'blur' }
                    ],
                }
            };
        },
        methods:{
            onload(){
                let _this=this;
              axios.get(this.url+'findPersonInfo?account='+sessionStorage.getItem("account"))
                  .then(function (data) {
                      _this.resetForm=data.data;
                  })
            },
            toAmend(){
                this.dialogVisible=true;
                },
            reset(){this.resetshow=0;},
            queren(resetForm){
                this.$refs[resetForm].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get(this.url+'updatePersonInfor',{
                            params:{
                                account:window.sessionStorage.getItem("account"),
                                password:sessionStorage.getItem("password"),
                                repassword:sessionStorage.getItem("password"),
                                name:_this.resetForm.name,
                                idCardNo:_this.resetForm.idCardNo
                            }
                        }).then(function (data) {
                            console.log(data);
                            if (data.data === 1) {
                                _this.resetshow=1;
                                _this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                            }
                        })
                    } else {
                        console.log("error");
                        return false;
                    }
                });


            },
            cancel(){
              this.dialogVisible=false;
              this.resetForm1.oldpassword='';
                this.resetForm1.newpassword='';
                this.resetForm1.repassword='';
            },
            resetpassword(resetForm1){

                this.$refs[resetForm1].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        if (this.resetForm1.oldpassword === sessionStorage.getItem("password")) {
                            axios.get(this.url+'updatePersonInfor',{
                                params:{
                                    account:window.sessionStorage.getItem("account"),
                                    password:this.resetForm1.newpassword,
                                    repassword:this.resetForm1.newpassword,
                                    name:_this.resetForm.name,
                                    idCardNo:_this.resetForm.idCardNo
                                }
                            }).then(function (data) {
                                if (data.data === 1) {
                                    _this.dialogmessage=true;
                                    setTimeout(function () {
                                        _this.backlogin();
                                    },3000);
                                }else {
                                    _this.$message({
                                        message: '发现未知异常，请联系管理员',
                                        type: 'error'
                                    });
                                }
                            })
                        }else {
                            _this.$message({
                                message: '旧密码不正确，请重新填写',
                                type: 'error'
                            });
                        }

                    } else {
                        console.log("error");
                        return false;
                    }
                });

            },
            backlogin(){
              this.$router.push({path:'/'});
              this.dialogmessage=false;
              this.sessionStorage.clear();
            },
        }
    };
</script>

<style scoped>
    .xiugai{
        width: 100%;
        height: 765px;
        background-size: 100% 100%;
        overflow: hidden;
    }

    .login-wrap {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 20px auto  -60px;
        width: 1000px;
        padding: 0px 35px 0px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login-wrap2 {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 100px auto;
        width: 600px;
        padding: 0px 35px 15px ;
    }
    h1{
        color: black;
        margin-top:20px ;
    }


</style>