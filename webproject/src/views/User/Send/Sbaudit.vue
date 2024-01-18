//出单审核
<template>
    <div class="page6">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>出单审核</h1>
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
                    <el-table-column prop="shipper" label="托运人"width="200">
                    </el-table-column>
                    <el-table-column prop="consignee" label="收货人"width="200">
                    </el-table-column>
                    <el-table-column prop="rcAuditStatus" label="收单方审核状态"width="140" :formatter="shou">
                    </el-table-column>
                    <el-table-column prop="signatureAuditStatus" label="签章方审核状态"width="140" :formatter="qian">
                    </el-table-column>
                    <el-table-column width="140"  label="提单详情" align="center">
                        <template slot-scope="scope">
                        <el-button  type="warning" icon="el-icon-zoom-in" @click="apply(scope.row,scope.$index)">详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="phone" label="" width="460">
                        <template slot-scope="scope">
                            <el-button  type="primary" icon="el-icon-edit" @click="revise(scope.row,scope.$index)">修改</el-button>
                            <el-button  type="success" icon="el-icon-check" @click="pass(scope.row,scope.$index)">通过</el-button>
                            <el-button  type="info" icon="el-icon-close" @click="reject(scope.row,scope.$index)">驳回</el-button>
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
            <el-dialog title="驳回意见" :visible.sync="dialogFormVisible"
                       :close-on-click-modal="false"
                       :close-on-press-escape="false"
                       :show-close="false">
                <el-col :span="24" class="col1">
                    <div class="ios"style="margin-right: 300px">
                        <p align="right">意见：<el-input type="text" v-model="detailsData.scfAuditOpinion" auto-complete="off" style="width: 240px" ></el-input></p>
                    </div>
                </el-col>
                <div slot="footer" class="dialog-footer">
                    <el-button  type="primary" @click="quxiao()">取 消</el-button>
                    <el-button type="primary" @click="hanson()">确 定</el-button>
                </div>
            </el-dialog>

            <el-dialog title="作废申请" :visible.sync="dialogFormVisible1"
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
                rowid:'',
                detailsData:{},
                currentPage:1, //当前页数
                ruleForm2:{
                    cause:'',
                },
                rules2:{
                    cause: [
                        { required: true,validator:validatePass , trigger: 'blur' }
                    ],
                },
                dialogFormVisible:false,
                dialogFormVisible1:false,
            }
        },
        created(){
            this.onload();
        },
        methods:{
            shou(row){
                if (row.rcAuditStatus === 1) {
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
                axios.get(this.url+'findsccompanyrecording',{
                    params:{
                        sendCompanyId:sessionStorage.getItem("companyid"),
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

            //详情
            apply(row,index){
                this.$router.push({
                    path:'/Index1/record',query:{
                        edit:row.blNo,
                        tupian:row.sendCompanyId
                    }
                })
            },
            //修改
            revise(row,index){
                this.$router.push({
                    path:'/Index1/record2',query:{
                        edit:row.blNo,
                        tupian:row.sendCompanyId
                    }
                })
            },
            //通过
            pass(row,index){
                let _this=this;
                axios.get(this.url+'scapply',{
                    params:{
                        account:sessionStorage.getItem('account'),
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
            reject(row,index){
                this.dialogFormVisible=true;
                this.blNo=row.blNo;
            },
            //驳回里的确认
            hanson(){
                let _this = this;
                if (!this.detailsData.scfAuditOpinion) {
                    this.$message.error('请输入意见')
                }else {
                    axios.get(this.url + 'scunapply', {
                        params: {
                            account: sessionStorage.getItem('account'),
                            blNo: this.blNo,
                            scfAuditOpinion: this.detailsData.scfAuditOpinion,
                        }
                    }).then(function (data) {
                        if (data.data === 1) {
                            _this.$message({
                                message: '驳回成功',
                                type: 'success'
                            });
                            _this.dialogFormVisible = false;
                            _this.onload();
                            _this.detailsData = {};
                        } else {
                            _this.$message.error('操作失败');
                        }
                    })
                }
            },
            //驳回里的取消
            quxiao(){
                this.dialogFormVisible=false;
                this.detailsData={};
            },
            //作废
            rebut(row,index){
              this.dialogFormVisible1=true;
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
                                _this.dialogFormVisible1=false;
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
              this.dialogFormVisible1=false;
            }
        }
    }
</script>
<style scoped>
    .page6{
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