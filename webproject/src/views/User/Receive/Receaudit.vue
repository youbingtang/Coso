//收单审核
<template>
    <div class="page20">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>收单审核</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 96%;margin-left:2% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="blNo" label="提单编码" width="280">
                    </el-table-column>
                    <el-table-column prop="shipper"  label="托运人" width="200">
                    </el-table-column>
                    <el-table-column prop="consignee" label="收货人"width="200">
                    </el-table-column>
                    <el-table-column prop="scAuditStatus" label="出单方审核状态"width="140" :formatter="chu">
                    </el-table-column>
                    <el-table-column prop="signatureAuditStatus" label="签章方审核状态"width="140" :formatter="qian">
                    </el-table-column>
                    <el-table-column label="提单详情"width="140">
                        <template slot-scope="scope">
                            <el-button  type="warning" icon="el-icon-zoom-in" @click="xiangqing(scope.row,scope.$index)">详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column  width="460">
                        <template slot-scope="scope">
                            <el-button  type="primary" icon="el-icon-edit" @click="revise1(scope.row,scope.$index)">修改</el-button>
                            <el-button  type="success" icon="el-icon-check" @click="apply(scope.row,scope.$index)">通过</el-button>
                            <el-button  type="info" icon="el-icon-close" @click="revise(scope.row,scope.$index)">驳回</el-button>
                            <el-button  type="danger" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">作废</el-button>
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
                url: "http://localhost:8888/Cosoproject/",
                tableData:[], //存放从后端传来的数据
                currentPage:1, //当前页数
                rowid:'',
                ruleForm2:{
                    cause:'',
                },
                rules2:{
                    cause: [
                        { required: true,validator:validatePass , trigger: 'blur' }
                    ],
                },
                dialogFormVisible:false,
            }
        },
        created(){
            this.onload();
        },
        methods:{
            xiangqing(row){
                this.$router.push({
                    path:'/Index1/record',query:{
                        edit:row.blNo,
                        tupian:row.sendCompanyId
                    }
                })
            },
            chu(row){
              if (row.scAuditStatus === 1) {
                  return '通过'
              }  else {
                  return '不通过'
              }
            },
            qian(row){
                if (row.signatureAuditStatus === 1) {
                    return '通过'
                }  else {
                    return '不通过'
                }
            },
            onload(){
                let _this=this;
                axios.get(this.url+'findallrecording',{
                    params:{
                        receiveCompanyId:sessionStorage.getItem("companyid"),
                        currentPage:this.currentPage
                    }}).then(function (data) {
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
            //修改
            revise1(row,index){
                 this.$router.push({
                     path:'/Index1/record2',query:{
                         edit:row.blNo,
                         tupian:row.sendCompanyId
                     }
                 })
             },
            //通过
            apply(row,index){
                let _this=this;
                axios.get(this.url+'rcapply',{
                    params:{
                        blNo:row.blNo,
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '通过成功',
                            type: 'success'});
                        _this.onload();
                    }else {
                        _this.$message.error('操作失败');
                    }
                })
            },
            //驳回
            revise(row,index){
                let _this=this;
                axios.get(this.url+'rcunapply',{
                    params:{
                        blNo:row.blNo,
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '驳回成功',
                            type: 'success'});
                        _this.onload();
                    }else {
                        _this.$message.error('操作失败');
                    }
                })
            },

            //作废
            rebut(row,index){
                this.dialogFormVisible=true;
                this.rowid=row.id;
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
        }
    }
</script>
<style scoped>
    .page20{
        width: 100%;
        height: 800px;
        background-size: 100% 100%;
        overflow: hidden;
    }
    h1{
        margin-left: -1400px;
        font-size:40px;
    }
    .box-card{
        background-color: transparent !important;
    }

</style>