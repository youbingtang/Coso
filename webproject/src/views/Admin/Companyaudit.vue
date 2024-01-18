//企业信息
<template>
    <div class="page6">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="20px"
                 class="demo-ruleForm2 ">
            <h1>企业信息</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 85%;margin-left:7% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="name" label="企业名称" width="300">
                    </el-table-column>
                    <el-table-column prop="province" label="所在省份" width="100">
                    </el-table-column>
                    <el-table-column prop="city" label="所在城市" width="100">
                    </el-table-column>
                    <el-table-column prop="cerificateAttachment" label="组织机构代码" width="140">
                    </el-table-column>
                    <el-table-column prop="creditCode" label="企业信用代码" width="140">
                    </el-table-column>
                    <el-table-column prop="account" label="管理员手机号" width="140">
                    </el-table-column>
                    <el-table-column prop="email" label="联系邮箱" width="140">
                    </el-table-column>
                    <el-table-column prop="phone" label="" width="300">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-check" @click="apply(scope.row,scope.$index)">详情
                            </el-button>
                            <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)" v-show="scope.row.isDel===1">启用
                            </el-button>
                            <el-button type="success" icon="el-icon-close" @click="rebut(scope.row,scope.$index)" v-show="scope.row.isDel===0">禁用
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-row :gutter="20">
                    <el-col :span="7" :offset="12">
                        <div class="block " style="margin-left: -600px;margin-top: 20px">
                            <table style="padding-left: 30%;padding-top: 30px;">
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
                <el-button type="primary" style="margin-left: -1400px;" @click="handleInsert">待审核企业</el-button>
            </el-card>

            <el-dialog title="信息详情" :visible.sync="dialogFormVisible"
                       style="width: 2600px;margin-left: -300px"
                       :close-on-click-modal="false"
                       :close-on-press-escape="false"
                       :show-close="false">
                <table width="1200px" class="table">
                    <tbody>
                    <tr><td>企业名称：</td><td>{{detailsData.name}}</td><td>企业类型：</td><td>{{detailsData.companyType}}</td></tr>
                    <tr><td>会员类型：</td><td>{{detailsData.memberType}}</td><td>所在省份：</td><td>{{detailsData.province}}</td></tr>
                    <tr><td>所在城市：</td><td>{{detailsData.city}}</td><td>审核状态：</td><td>{{(detailsData.status===1?'通过':'未通过')}}</td></tr>
                    <tr><td>详细地址：</td><td colspan="3">{{detailsData.address}}</td></tr>
                    <tr><td>是否删除：</td><td>{{(detailsData.isDel===1?'是':'否')}}</td><td>是否开通签章服务：</td><td>{{(detailsData.isSignature===1?'是':'否')}}</td></tr>
                    <tr><td>企业信用代码：</td><td>{{detailsData.creditCode}}</td><td>组织机构代码证：</td><td>{{detailsData.cerificateAttachment}}</td></tr>
                    <tr><td>企业联系邮箱：</td><td>{{detailsData.email}}</td><td>企业管理员联系电话：</td><td>{{detailsData.account}}</td></tr>
                    <tr><td>是否开通出单服务：</td><td>{{(detailsData.isSendBl===1?'是':'否')}}</td><td>是否开通发单服务：</td><td>{{(detailsData.isReceive===1?'是':'否')}}</td></tr>
                    <!--<tr><td>企业logo：</td><td>  <el-image :src="'D:\\\upload\\logo\\'+detailsData1.logofile" ></el-image></td><td>企业seal：</td><td>  <el-image :src="'D:\\\upload\\signature\\'+detailsData2.seafile" ></el-image></td></tr>-->
                    <tr><td>企业logo状态：</td><td>{{detailsData1.blconfirmStatus===1?'启用':'禁用'}}</td><td>企业seal状态：</td><td>{{detailsData2.sconfirmStatus}}</td></tr>
                    </tbody>
                </table>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="habut()">返  回</el-button>
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
                detailsData:{},
                detailsData1:{},//图片
                detailsData2:{
                    sconfirmStatus:'',
                },
                currentPage:1, //当前页数
                dialogFormVisible:false,

                ruleForm2:{
                },
                rules2:{
                }
            }
        },
        created(){
            let _this=this;
            axios.get(this.url+'selectAllCompanyInfo'
            ).then(function (data) {
                _this.tableData = data.data;
            })
        },
        methods:{
            onload(){
                let _this=this;
                axios.get(this.url+'selectAllCompanyInfo',{
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
            //详情
            apply(row,index){
                let _this=this;
                this.dialogFormVisible=true;
                _this.detailsData=this.tableData.list[index];

                axios.get(this.url+"selectCompanyLogo",{
                    params:{
                        cid:row.id,
                    }}).then(function (data) {
                    _this.detailsData1=data.data
                });
                axios.get(this.url+"selectCompanySeal",{
                    params:{
                        cid:row.id,
                    }}).then(function (data) {
                    if (data.data.length===0){
                        _this.detailsData2.sconfirmStatus='暂无信息'
                    } else{
                        for (let i = 0; i < data.data.length; i++) {
                            if (data.data[i].sealType === 1) {
                                if (data.data[i].isEnable===0) {
                                    _this.detailsData2.sconfirmStatus='签章未启用'
                                }else {
                                    _this.detailsData2.sconfirmStatus='签章已启用'
                                }
                            }else{
                                if (data.data[i].isEnable===0) {
                                    _this.detailsData2.sconfirmStatus='印章未启用'
                                }else {
                                    _this.detailsData2.sconfirmStatus='印章已启用'
                                }
                            }
                        }
                    }
                })
            },
            //删除
            rebut(row,index){
                let _this=this;
                axios.get(this.url+"delUserAndCompany",
                    {
                        params:{
                            cid:row.id
                        }}).then(function (data) {
                    if(data.data===11){
                        _this.$message('成功');
                    }
                });
                axios.get(this.url+'selectAllCompanyInfo'
                ).then(function (data) {
                    _this.tableData = data.data;
                })

            },
            handleInsert(){
                this.$router.push({path:'/Index2/companyaudit1'});
            },
            habut(){
                this.dialogFormVisible=false;
                this.detailsData={};
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