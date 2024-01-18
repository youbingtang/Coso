//提单信息追踪
<template>
    <div class="page6d">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>提单信息追踪</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 76% ;margin-left:12%"
                        selection-change="handleSelectionChange">
                    <el-table-column prop="blNo" label="提单编码" width="250">
                    </el-table-column>
                    <el-table-column prop="sendCompany" label="出单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="receiveCompany" label="收单公司" width="200">
                    </el-table-column>
                    <el-table-column prop="signatureCompany" label="签章公司" width="200">
                    </el-table-column>
                    <el-table-column  label="操作" width="380">
                        <template slot-scope="scope">
                            <el-button type="success" icon="el-icon-edit" @click="details(scope.row,scope.$index)">详情
                            </el-button>
                            <el-button type="primary" icon="el-icon-check" @click="apply(scope.row,scope.$index)">通过
                            </el-button>
                            <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">驳回
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
                ruleForm2: {},
                rules2: {},
            }
        },
        created() {
            this.onload();
        },
        methods: {
            onload(){
                let _this=this;
                axios.get(this.url+'findAllStatesIsTwo',{
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
                axios.get(this.url+'pingTaiApply',{
                    params:{
                        blNo:row.blNo
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '通过',
                            type: 'success'
                        });
                        _this.onload();
                    }else {
                        _this.$message({
                            message: '操作出现异常',
                            type: 'error'
                        });
                    }
                });
            },
            //驳回
            rebut(row, index) {
                let _this=this;
                axios.get(this.url+'pingTaiUnapply?blNo='+row.blNo)
                    .then(function (data) {
                        if(data.data===1){
                            _this.$message({
                                message: '驳回',
                                type: 'success'
                            });
                            _this.onload();
                        }else {
                            _this.$message({
                                message: '操作出现异常',
                                type: 'error'
                            });
                        }
                    })
            },
            //详情
            details(row, index) {
            this.$router.push({
                path:'/Index2/record',query:{
                    edit:row.blNo,
                    tupian:row.sendCompanyId
                }
            })
            },
        },


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

</style>