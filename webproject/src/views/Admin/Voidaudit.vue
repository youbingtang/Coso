//作废审核
<template>
    <div class="page6d1">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>作废审核</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 55% ;margin-left:20%"
                        selection-change="handleSelectionChange">

                    <el-table-column prop="blNo" label="提单编码" width="200">
                    </el-table-column>
                    <el-table-column prop="cause" label="作废原因" width="200">
                    </el-table-column>
                    <el-table-column prop="applyCompanyName" label="申请企业" width="220">
                    </el-table-column>
                    <el-table-column label="操作" width="240">
                        <template slot-scope="scope">
                            <el-button type="success" icon="el-icon-check" @click="details(scope.row,scope.$index)">同意
                            </el-button>
                            <el-button type="danger" icon="el-icon-close" @click="nodetails(scope.row,scope.$index)">不同意
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
            <el-dialog title="意见" :visible.sync="dialogFormVisible"
                       :close-on-click-modal="false"
                       :close-on-press-escape="false"
                       :show-close="false">
                <div class="ios"style="margin-right: 200px">
                    <el-form :model="ruleForm2":rules="rules2"  status-icon  style="margin-left: 270px"
                             ref="ruleForm2" label-width="100px" class="demo-addAccount" label-position="left" >
                        <el-form-item label="意见 " prop="cause">
                            <el-input v-model="ruleForm2.cause"></el-input>
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
                dialogFormVisible:false,
                url:'http://localhost:8888/Cosoproject/',
                tableData: [], //存放从后端传来的数据
                currentPage: 1, //当前页数
                rowid:'',
                ruleForm2: {
                    cause:'',
                },
                rules2: {
                    cause: [
                        { required: true,validator:validatePass , trigger: 'blur' }
                    ],
                },
            }
        },
        created() {
            this.onload();
        },
        methods: {
            onload(){
                let _this=this;
                axios.get(this.url+'findblNoZuoFeiByAll',{
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
            //同意
            details(row, index) {
                let _this=this;
              axios.get(this.url+'zuofeiapply',{
                  params:{
                      account:sessionStorage.getItem('account'),
                      blId:row.blid
                  }
              }).then(function (data) {
                  if(data.data===2){
                      _this.$message({
                          message: '操作成功',
                          type: 'success'
                      });
                      _this.onload();
                  }else {
                      _this.$message({
                          message: '操作失败，请联系管理员',
                          type: 'error'
                      });
                  }
              })
            },
            //不同意
            nodetails(row,index){
               this.dialogFormVisible=true;
               this.rowid=row.bid
            },
            //不同意里的发送
            hand(ruleForm2){
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this=this;
                        axios.get(this.url+'updateBlNoByJujue',{
                            params:{
                                bid:this.rowid,
                                platformAuditOpinion:this.ruleForm2.cause,
                            }
                        }).then(function (data) {
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

            //不同意里的返回
            ret(){
                this.ruleForm2={};
                this.dialogFormVisible=false;
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