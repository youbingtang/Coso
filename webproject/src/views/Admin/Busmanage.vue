//平台服务申请审核
<template>
    <div>
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm page2">
            <h1>服务申请审核</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 58%;margin-left:20% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="name" label="申请公司" width="300">
                    </el-table-column>
                    <el-table-column prop="type"  label="开通服务类型" width="140" :formatter="stateFormat">
                    </el-table-column>
                    <el-table-column prop="adminAccount" label="申请人账号" width="140">
                    </el-table-column>
                    <el-table-column prop="createDate" label="申请时间" width="140">
                    </el-table-column>
                    <el-table-column width="230"  label="" >
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-edit" @click="apply(scope.row,scope.$index)">详情</el-button>
                            <el-button type="warning" @click="hanson(scope.row)">驳 回 </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-row :gutter="20">
                    <el-col :span="7" :offset="12">
                        <div class="block " style="margin-left: -600px;margin-top: 20px">
                            <table style="padding-left: 25%;padding-top: 30px">
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
        <el-dialog title="信息详情" :visible.sync="dialogFormVisible"
                   style="width: 2600px;margin-left: -300px"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <table width="1200px" class="table">
                <tbody>
                <tr><td>申请企业名称：</td><td>{{detailsData.name}}</td><td>服务开通类型：</td><td>{{type}}</td></tr>
                <tr><td>申请人帐号：</td><td>{{detailsData.adminAccount}}</td><td>申请时间：</td><td>{{detailsData.createDate}}</td></tr>
                <tr><td>{{(detailsData1.sealType===1?'印章名称':'签章名称')}}</td><td colspan="3">{{detailsData1.sealname}} </td></tr>
                <tr><td>源文件</td><td colspan="3">
                    <el-image :src="src" style="width: 480px;height: 200px">
                        <div slot="placeholder" class="image-slot">
                            加载中<span class="dot">...</span>
                        </div>
                        <!--<div slot="error" class="image-slot" style="margin-top: 18%">-->
                        <!--暂无图片信息-->
                        <!--</div>-->
                    </el-image></td></tr>
                </tbody>
            </table>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="back()">返  回</el-button>
                <el-button type="primary" @click="habut()">通  过</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    export default {
        data() {
            return {
                src:'http://localhost:8888/signature/',
                url:'http://localhost:8888/Cosoproject/',
                currentPage:1, //当前页数
                tableData: [], //存放从后端传来的数据
                type:'',
                detailsData:{
                    name:'',
                    adminAccount:'',
                    createDate:'',
                },
                detailsData1:{
                    sealType:'',
                    sealname:'',
                },
                rowid:'',
                ruleForm2:{},
                rules2: {},
                dialogFormVisible:false,
            }
        },
        created(){
            this.onload();
        },
        methods:{
            onload(){
                let _this=this;
                axios.get(this.url+'selectAllServiceApplay',{
                    params:{
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
            stateFormat(row){
                if (row.type === 0) {
                    return "收单服务";
                }else if (row.type === 1) {
                    return "出单服务";
                }else if (row.type === 2) {
                    return "签章服务";
                }
            },
            back(){
                this.detailsData1.sealname='';
                this.detailsData1.sealType='';
                this.detailsData.name='';
                this.detailsData.adminAccount='';
                this.detailsData.createDate='';
                this.type='';
                this.dialogFormVisible=false;
                this.src='http://localhost:8888/signature/';
            },
            //详情
            apply(row, index) {
                let _this=this;
                this.rowid=row.id;
                this.dialogFormVisible=true;
                this.detailsData.name=this.tableData.list[index].name;
                this.detailsData.adminAccount=this.tableData.list[index].adminAccount;
                this.detailsData.createDate=this.tableData.list[index].createDate;
                axios.get(this.url+'selectCompanySeal',{
                    params:{
                        cid:row.id
                    }}).then(function (data) {
                    if (data.data.length === 0) {
                            _this.type='收单服务';
                            _this.detailsData1.sealname='暂无信息';
                            _this.detailsData1.sealType=1;
                        }else {
                        if (row.type === 1){
                                _this.type='出单服务';
                                for ( let i=0;i<data.data.length;i++){
                                    if ( data.data[i].sealType===1){
                                        _this.src += data.data[i].seafile;
                                        _this.detailsData1.sealname=data.data[i].sealname;
                                    }
                                    _this.detailsData1.sealType=0;
                                }
                            }else if (row.type === 2) {
                                _this.type='签章服务';
                                for (let i=0;i<data.data.length;i++){
                                    if ( data.data[i].sealType===0){
                                        _this.src += data.data[i].seafile;
                                        _this.detailsData1.sealname=data.data[i].sealname;
                                    }
                                    _this.detailsData1.sealType=1;
                                }
                            }
                        }

                    console.log(data);

                })
            },
            //通过
            habut(){
                let _this=this;
                axios.get(this.url+'applyService', {
                        params:{
                            cid:this.rowid
                        }}).then(function (data) {
                    if(data.data===1){
                        _this.dialogFormVisible=false;
                        _this.$message('通过申请');
                        _this.onload();
                    }
                });
            },
            //驳回
            hanson(row){
                let _this=this;
                axios.get(this.url+'unapplyService',
                    {
                        params:{
                            cid:row.id
                        }}).then(function (data) {
                    if(data.data===2){
                        _this.$message('驳回申请');
                    }
                });
                axios.get(this.url+'selectAllServiceApplay',{
                })
                    .then(function (data) {
                        _this.tableData=data.data

                    });
                this.dialogFormVisible=false;
            }
        },

    }
</script>
<style scoped>
    .page2{
        width: 100%;
        height: 800px;
        background-size: 100% 100%;
        overflow: hidden;

    }
    h1{
        margin-left:-1400px ;
        font-size:40px;
    }
    .box-card{
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
    .table thead th
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