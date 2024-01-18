//平台邮寄
<template>
    <div class="page6d1">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>提单邮寄</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 66% ;margin-left:17%"
                        selection-change="handleSelectionChange">

                    <el-table-column prop="blNo" label="提单编码" width="200">
                    </el-table-column>
                    <el-table-column prop="sendCompanyId" label="出单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="receiveCompanyId" label="签章公司" width="220">
                    </el-table-column>
                    <el-table-column prop="signatureCompanyId" label="收单公司" width="220">
                    </el-table-column>
                    <el-table-column label="操作" width="240">
                        <template slot-scope="scope">
                            <el-button type="success" icon="el-icon-check" @click="details(scope.row,scope.$index)">邮寄
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
            </el-card>
            <el-dialog title="邮寄" :visible.sync="dialogFormVisible"
                       :close-on-click-modal="false"
                       :close-on-press-escape="false"
                       :show-close="false">
                <div class="ios"style="margin-right: 200px">
                    <el-form :model="ruleForm2":rules="rules2"  status-icon  style="margin-left: 270px"
                             ref="ruleForm2" label-width="100px" class="demo-addAccount" label-position="left" >
                        <el-form-item label="快递单号 " prop="No">
                            <el-input v-model="ruleForm2.No"></el-input>
                        </el-form-item>
                        <el-form-item label="邮寄公司 " prop="company">
                            <el-select v-model="ruleForm2.company" placeholder="请选择"  style="width: 350px">
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
                    <el-button type="primary" @click="hand('ruleForm2')">发 送</el-button>
                </div>
            </el-dialog>
        </el-form>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'

    export default {
        data() {
            var validatePass1 = (rule, value, callback) => {
                var req = /^[0-9a-zA-Z_]*$/;
                if (!value) {
                    return callback(new Error("请输入邮寄单号"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是数字和字母"));
                } else if (value.toString().length < 9 || value.toString().length > 17) {
                    return callback(new Error("长度在9到17位之间"));
                } else {
                    return callback();
                }
            };
            return {
                url:'http://localhost:8888/Cosoproject/',
                tableData: [], //存放从后端传来的数据
                currentPage: 50, //当前页数
                rowid:'',
                ruleForm2: {
                    company:'',
                    options1:'',
                    No:''
                },
                rules2: {
                    company: [{required: true, message: '请选择', trigger: 'blur'}],
                    No: [{required: true,  validator: validatePass1, trigger: 'blur'}],
                },
                dialogFormVisible:false,
            }
        },
        created() {
            this.onload();
        },
        methods: {
            onload(){
                let _this=this;
                axios.get(this.url+'findAllstatesissix',{
                    params:{
                        currentPage:this.currentPage
                    }
                }).then(function (data) {
                    _this.tableData = data.data;
                })
            },
            sub(){
                if (this.tableData.hasPreviousPage){
                    this.currentPage --;
                    this.onload();
                }
            },
            add(){
                if (this.tableData.hasNextPage){
                    this.currentPage ++;
                    this.onload()   ;
                }
            },
            //邮寄
            details(row, index) {
                let _this=this
               this.dialogFormVisible=true;
               this.rowid=row.id;
               axios.get(this.url+'findAllMallingcompany'
               ).then(function (data) {
                   _this.ruleForm2.options1=data.data
               })

            },
            //邮寄里的发送
            hand(ruleForm2){
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this=this;
                        axios.get(this.url+'malling',{
                            params:{
                                blNoid:this.rowid,
                                recordUserAccount:sessionStorage.getItem('account'),
                                orderNo:this.ruleForm2.No,
                                expressCompanyId:this.ruleForm2.company,

                            }
                        }).then(function (data) {
                            console.log(data);
                            if(data.data===1){
                                _this.$message({
                                    message: '操作成功',
                                    type: 'success'
                                });
                                _this.onload();
                                _this.ruleForm2={};
                                _this.dialogFormVisible=false;
                            }else {
                                _this.$message({
                                    message: '操作失败',
                                    type: 'error'
                                });
                            }
                        })
                    } else {
                        return false;
                    }
                });
             },
            //邮寄里的返回
            ret(){
                this.dialogFormVisible=false;
                this.ruleForm2={};
            }
        },


    }
</script>
<style scoped>
    .page6d1 {
        width: 100%;
        height: 800px;
        background-size: 100% 100%;
        overflow: hidden;
    }

    h1 {
        margin-left: -1400px;
        font-size:40px;
    }

    .box-card {
        background-color: transparent !important;
    }

</style>