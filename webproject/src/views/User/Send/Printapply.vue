//打印管理
<template>
    <div class="page6d1">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>打印申请</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 57% ;margin-left:19%"
                        selection-change="handleSelectionChange">
                    <el-table-column prop="blNo" label="提单编码" width="200">
                    </el-table-column>
                    <el-table-column prop="sendCompanyId" label="出单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="receiveCompanyId" label="收单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="signatureCompanyId" label="签章公司" width="200">
                    </el-table-column>
                    <el-table-column label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="success" icon="el-icon-edit" @click="details(scope.row,scope.$index)">打印支付
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

    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'

    export default {
        data() {
            return {
                url:'http://localhost:8888/Cosoproject/',
                tableData: [], //存放从后端传来的数据
                currentPage: 1, //当前页数
                detailsData:{},
                ruleForm2: {},
                rules2: {},
                dialogFormVisible:false,
                blNo:'',
            }
        },
        created() {
            this.onload();
        },
        methods: {
            onload(){
                let _this=this;
                axios.get(this.url+'findAllStatesidThree',{
                    params:{
                        cid:sessionStorage.getItem("companyid"),
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
            //打印
            details(row, index) {
                let  _this=this;
                axios.get(this.url+"selectSendPriceByblNo?blNo="+row.blNo)
                    .then((data)=> {
                        console.log(data.data.blNo);
                        _this.$router.push({path:'/Index1/pay'});
                        sessionStorage.setItem("blNo",data.data.blNo);
                    });
            },
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