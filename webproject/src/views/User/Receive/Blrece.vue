//提单签收
<template>
    <div class="page17">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 label-width="0px"
                 class="demo-ruleForm2 ">
            <h1>提单签收</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 60%;margin-left:20% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="blNo" label="提单编码" width="280">
                    </el-table-column>
                    <el-table-column prop="shipper"  label="托运人" width="140">
                    </el-table-column>
                    <el-table-column prop="consignee" label="收货人"width="140">
                    </el-table-column>
                    <el-table-column prop="billStatus" label="提单状态"width="140" :formatter="formatter">
                    </el-table-column>
                    <el-table-column  width="140">
                        <template slot-scope="scope">
                            <el-button  type="primary" icon="el-icon-edit" @click="apply(scope.row,scope.$index)">签收</el-button>
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
                url: "http://localhost:8888/Cosoproject/",
                tableData:[], //存放从后端传来的数据
                currentPage:1, //当前页数
                ruleForm2:{
                },
                rules2:{
                }
            }
        },
        created(){
            this.onload();
        },
        methods:{
            formatter(row){
                if (row.billStatus === 8) {
                    return '邮寄中'
                }
            },
            onload(){
                let _this=this;
                axios.get(this.url+'findtidanbymallingid',{
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

            //签收
            apply(row,index){
                let _this=this;
                axios.get(this.url+'ok',{
                    params:{
                        blNo:row.blNo
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '签收成功',
                            type: 'success'});
                        _this.onload();
                    }
                })

            },
        }
    }
</script>
<style scoped>
    .page17{
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