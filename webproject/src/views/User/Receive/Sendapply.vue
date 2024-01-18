//邮寄申请
<template>
    <div class="page19">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>邮寄申请</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 73%;margin-left:15% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="blNo" label="提单编码" width="280">
                    </el-table-column>
                    <el-table-column prop="shipper"  label="托运人" width="140">
                    </el-table-column>
                    <el-table-column prop="consignee" label="收货人"width="140">
                    </el-table-column>
                    <el-table-column prop="scAuditStatus" label="出单方审核状态"width="140" :formatter="chu">
                    </el-table-column>
                    <el-table-column prop="signatureAuditStatus" label="签章方审核状态"width="140" :formatter="qian">
                    </el-table-column>
                    <el-table-column  width="180">
                        <template slot-scope="scope">
                            <el-button  type="primary" icon="el-icon-edit" @click="apply(scope.row,scope.$index)">申请邮寄</el-button>
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
            <el-dialog title="填写邮寄信息" :visible.sync="dialogFormVisible"
                       :close-on-click-modal="false"
                       :close-on-press-escape="false"
                       :show-close="false">
                <el-col :span="24" class="col1">
                    <div class="ios"style="margin-right: 300px">
                        <p align="right">收件人：<el-input type="text" v-model="detailsData.name" auto-complete="off" style="width: 240px" ></el-input></p>
                        <p align="right">收件人地址：<el-input type="text" v-model="detailsData.address" auto-complete="off" style="width: 240px" ></el-input></p>
                        <p align="right">收件人电话：<el-input type="text" v-model="detailsData.phone" auto-complete="off" style="width: 240px" ></el-input></p>
                    </div>
                </el-col>
                <div slot="footer" class="dialog-footer">
                    <el-button  type="primary" @click="quxiao()">取 消</el-button>
                    <el-button type="primary" @click="hanson()">确 定</el-button>
                </div>
            </el-dialog>
        </el-form>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    export default {
        data() {
            return {
                url: "http://localhost:8888/Cosoproject/",
                tableData:[], //存放从后端传来的数据
                currentPage:1, //当前页数
                detailsData:{},
                ruleForm2:{},
                rules2:{},
                dialogFormVisible:false,
                blNo:'',
                blId:'',
            }
        },
        created(){
            this.onload();
        },
        methods:{
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
                axios.get(this.url+'findAllStatesisFive',{
                    params:{
                        receiveCompanyId:sessionStorage.getItem("companyid"),
                        currentPage:this.currentPage
                    }}).then(function (data) {
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

            //通过
            apply(row,index){
                this.dialogFormVisible=true;
                this.blId=row.id;
                this.blNo=row.blNo;

            },
            //通过里的确认
            hanson(){
                let _this=this;
                if (!this.detailsData.name) {
                    this.$message.error('请输入姓名')
                } else if(!this.detailsData.address){
                    this.$message.error('请输入地址')
                }else if(!this.detailsData.phone){
                    this.$message.error('请输入电话')
                }else if(this.detailsData.phone != null ) {
                    var reg = /^1(3|4|5|6|7|8|9)\d{9}$/;
                    if (!reg.test(this.detailsData.phone)) {
                        this.$message.error("请输入有效电话！");
                    } else {
                        axios.get(this.url + 'applyForMailing', {
                            params: {
                                blId: this.blId,
                                blNo: this.blNo,
                                collectPerson: this.detailsData.name,
                                collectAddress: this.detailsData.address,
                                collectPhone: this.detailsData.phone,
                            }
                        }).then(function (data) {
                            console.log(data);
                            if (data.data === 1) {
                                _this.$message({
                                    message: '申请成功',
                                    type: 'success'
                                });
                                _this.dialogFormVisible = false;
                                _this.onload();
                            } else {
                                _this.$message.error('修改失败');
                            }
                        })
                    }
                }
            },
            //通过里的取消
            quxiao(){
                this.dialogFormVisible=false;
                this.detailsData={};
            },
        }
    }
</script>
<style scoped>
    .page19{
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