//出单申请
<template>
    <div class="page6d2">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>出单申请</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 61% ;margin-left:20%"
                        selection-change="handleSelectionChange">
                    <el-table-column prop="blNo" label="提单编码" width="200">
                    </el-table-column>
                    <el-table-column prop="receiveCompanyId" label="收单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="signatureCompanyId" label="签章公司" width="200">
                    </el-table-column>
                    <el-table-column label="提单详情" width="120">
                        <template slot-scope="scope">
                            <el-button type="success" icon="el-icon-edit" @click="details(scope.row,scope.$index)">详情
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column  label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-check" @click="apply(scope.row,scope.$index)">申请
                            </el-button>
                        </template>
                    </el-table-column>
                    <el-table-column  width="120">
                        <template slot-scope="scope">
                            <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">作废
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
        </el-form>

        <el-dialog title="作废申请" :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <div class="ios"style="margin-right: 200px">
                <el-form :model="ruleForm2":rules="rules2"  status-icon  style="margin-left: 270px"
                         ref="ruleForm2" label-width="100px" class="demo-addAccount" label-position="left" >
                    <el-form-item label="作废理由 " prop="cause">
                        <el-input v-model="ruleForm2.cause"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="ret()">返  回</el-button>
                <el-button type="primary" @click="hand('ruleForm2')">发 送</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'

    export default {
        data() {
            var validatePass = (rule, value, callback) => {
                var req = /^[\u4e00-\u9fa5]+$/;
                if (!value) {
                    return callback(new Error("请输入原因"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是中文"));
                } else if (value.toString().length > 20) {
                    return callback(new Error("长度为小于20位"));
                } else {
                    return callback();
                }
            };
            return {
                url:'http://localhost:8888/Cosoproject/',
                tableData: [], //存放从后端传来的数据
                currentPage: 1, //当前页数
                detailsData:{},
                rowid:'',
                ruleForm2: {
                    cause:'',},
                rules2: {
                    cause: [
                        { required: true,validator:validatePass , trigger: 'blur' }
                    ],
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
                axios.get(this.url+'findAllStatesIsOne',{
                    params:{
                        cid:sessionStorage.getItem("companyid"),
                        currentPage:this.currentPage
                    }
                }).then(function (data) {
                    console.log(data);
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
            //申请
            apply(row, index) {
                let _this=this;
                axios.get(this.url+'sendRecordingtoPingtai',{
                    params:{
                        blNo:row.blNo
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '申请成功，等待审核',
                            type: 'success'});
                        _this.onload();
                    }else {
                        _this.$message.error('操作失败');
                    }

                });
            },
            //作废
            rebut(row, index) {
                this.dialogFormVisible=true;
                this.rowid=row.id;

            },
            //详情
            details(row, index) {
                this.$router.push({
                    path:'/Index1/record',query:{
                        edit:row.blNo,
                        tupian:row.sendCompany
                    }
                })
            },
            //作废里的确认
            hand(ruleForm2){
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this=this;
                        axios.get(this.url+'zuofei',{
                            params:{
                                blId:this.rowid,
                                applyCompanyName:sessionStorage.getItem('cname'),
                                account:sessionStorage.getItem('account'),
                                applyUserName:sessionStorage.getItem('name'),
                                cause:this.ruleForm2.cause
                            }
                        }).then(function (data) {
                            if(data.data===1){
                                _this.$message({
                                    message: '申请发送成功',
                                    type: 'success'});
                                _this.dialogFormVisible=false;
                                _this.onload();
                                _this.ruleForm2={};
                            }else {
                                _this.$message.error('操作失败');

                            }
                        })
                    } else {
                        return false;
                    }
                });
            },

            //作废里的返回
            ret(){
                this.ruleForm2={};
                this.dialogFormVisible=false;
            }
        },


    }
</script>
<style scoped>
    .page6d2 {
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