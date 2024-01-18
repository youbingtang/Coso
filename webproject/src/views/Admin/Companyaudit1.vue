//平台待审核企业
<template>
    <div class="page6d">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>待审核企业</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 85% ;margin-left:8%"
                        selection-change="handleSelectionChange">
                    <el-table-column prop="name" label="企业名称" width="277">
                    </el-table-column>
                    <el-table-column prop="province" label="所在省份" width="80">
                    </el-table-column>
                    <el-table-column prop="city" label="所在城市" width="80">
                    </el-table-column>
                    <el-table-column prop="cerificateAttachment" label="组织机构代码" width="160">
                    </el-table-column>
                    <el-table-column prop="creditCode" label="企业信用代码" width="160">
                    </el-table-column>
                    <el-table-column prop="adminAccount" label="管理员手机号" width="110">
                    </el-table-column>
                    <el-table-column prop="email" label="联系邮箱" width="160">
                    </el-table-column>
                    <el-table-column prop="phone" label="" width="360">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-check" @click="apply(scope.row,scope.$index)">通过
                            </el-button>
                            <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">驳回
                            </el-button>
                            <el-button type="success" icon="el-icon-edit" @click="details(scope.row,scope.$index)">详情
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
                <el-button type="primary" style="margin-left: -1400px;" @click="back()">返回</el-button>
            </el-card>
        </el-form>


        <el-dialog title="驳回原因" :visible.sync="dialogFormVisible">
            <p>原因：<el-input v-model="reasion" style="width: 500px"></el-input></p>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="hanson()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="信息详情" :visible.sync="dialogFormVisible1" style="width: 2600px;margin-left: -300px"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <table width="1200px" class="table">
                <tbody>
                <tr><td>企业名称：</td><td>{{detailsData.name}}</td><td>企业类型：</td><td>{{detailsData.companyType}}</td></tr>
                <tr><td>会员类型：</td><td>{{detailsData.memberType}}</td><td>所在省份：</td><td>{{detailsData.province}}</td></tr>
                <tr><td>所在城市：</td><td>{{detailsData.city}}</td><td>审核状态：</td><td>{{(detailsData.status===1?'通过':'未通过')}}</td></tr>
                <tr><td>详细地址：</td><td colspan="3">{{detailsData.address}}</td></tr>
                <tr><td>是否删除：</td><td>{{(detailsData.isDel===1?'否':'是')}}</td><td>是否开通签章服务：</td><td>{{(detailsData.column_22===1?'是':'否')}}</td></tr>
                <tr><td>企业信用代码：</td><td>{{detailsData.creditCode}}</td><td>组织机构代码证：</td><td>{{detailsData.cerificateAttachment}}</td></tr>
                <tr><td>企业联系邮箱：</td><td>{{detailsData.email}}</td><td>企业管理员联系电话：</td><td>{{detailsData.adminAccount}}</td></tr>
                <tr><td>是否开通出单服务：</td><td>{{(detailsData.isSendBl===1?'是':'否')}}</td><td>是否开通发单服务：</td><td>{{(detailsData.isReceive===1?'是':'否')}}</td></tr>
                </tbody>
            </table>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="habut()">返  回</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'

    export default {
        data() {
            return {
                url:'http://localhost:8888/Cosoproject/',
                tableData: [], //存放从后端传来的数据
                detailsData:{},
                currentPage: 1, //当前页数
                ruleForm2: {},
                rules2: {},
                reasion:'',
                rowid:'',
                formLabelWidth: '120px',
                dialogFormVisible:false,
                dialogFormVisible1:false,
            }
        },
        created() {
            let _this = this;
            axios.get(this.url+'findAllUndifindCompany'
            ).then(function (data) {
                _this.tableData = data.data;
            })
        },
        methods: {
            back(){
              this.$router.back();
            },
            quxiao(){
                this.dialogFormVisible = false;
                this.reasion='';
            },
            onload(){
                let _this=this;
                axios.get(this.url+'findAllUndifindCompany',{
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
            //通过
            apply(row, index) {
                let _this=this;
                axios.get(this.url+'confim',{
                    params:{
                        id:row.id
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.$message('通过审核');
                    }else if(data.data===-1){
                        _this.$message('未通过审核');
                    }
                });
                this.onload();
            },
            //驳回
            rebut(row, index) {
                this.dialogFormVisible=true;
                this.rowid=row.id;

            },
            //详情
            details(row, index) {
                this.dialogFormVisible1=true;
                this.detailsData=this.tableData.list[index]

            },
            //确认
            hanson(){
                let _this=this;
                axios.get(this.url+'unconfim',{
                    params:{
                        id:this.rowid
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.$message({
                            message: '驳回申请',
                            type: 'success'
                        });
                        axios.get('http://localhost:8888/Cosoproject/findAllUndifindCompany'
                        ).then(function (data) {
                            console.log(data);
                            _this.tableData = data.data.list;
                        })
                    }else if(data.data===-1){
                        _this.$message({
                            message: '驳回失败',
                            type: 'error'
                        });
                    }
                });
                sessionStorage.setItem("reasion",_this.reasion);
                this.dialogFormVisible=false;
            },
            //返回
            habut(){
                this.dialogFormVisible1=false;
                this.detailsData={};
            },
        }
    }
</script>
<style scoped>
    .page6d {
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
    .table
    {
        border-collapse: collapse;
        margin: 0 auto;
        text-align: center;
    }
    .table td, table th
    {
        border: 1px solid #cad9ea;
        color: #666;
        height: 30px;
    }
    table thead th
    {
        background-color: #CCE8EB;
        width: 100px;
    }
    .table tr:nth-child(odd)
    {
        background: #fff;
    }
    .table tr:nth-child(even)
    {
        background: #F5FAFA;
    }

</style>