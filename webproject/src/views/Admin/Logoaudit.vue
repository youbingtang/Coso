//Logo管理
<template>
    <div class="page7">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 class="demo-ruleForm2 ">
            <h1>Logo管理</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 80% ;margin-left:10%"
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="company" label="企业名称" width="300">
                    </el-table-column>
                    <el-table-column prop="name" label="logo名称" width="210">
                    </el-table-column>
                    <el-table-column prop="file" label="logo源文件" width="240">
                        <template slot-scope="scope">
                        <el-image :src="src+scope.row.file" style="width: 90px; height: 60px" :preview-src-list="[src+scope.row.file]">
                            <div slot="placeholder" class="image-slot">
                                加载中<span class="dot">...</span>
                            </div>
                            <div slot="error" class="image-slot" style="margin-top: 20px">
                                暂无logo信息
                            </div>
                        </el-image>
                        </template>
                    </el-table-column>
                    <el-table-column prop="uploadDay" label="上传日期" width="140">
                    </el-table-column>
                    <el-table-column prop="isEnable" label="启用状态" width="140" :formatter="formatter">
                    </el-table-column>
                    <el-table-column label="" width="280">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-check" @click="rebut(scope.row,scope.$index)">通过
                            </el-button><el-button type="info" icon="el-icon-close" @click="no(scope.row,scope.$index)">不通过
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
                src:'http://localhost:8888/logo/',
                url: "http://localhost:8888/Cosoproject/",
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
                axios.get(this.url+'selectUnDefindLogo',{
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
            rebut(row, index) {
                let _this = this;
                axios.get(this.url + 'updateLogoApply', {
                    params: {
                        cid:row.cid
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.$message({
                            message: '操作成功',
                            type: 'success'
                        });
                        _this.onload();
                    } else {
                        _this.$message({
                            message: '操作失败',
                            type: 'error'
                        });
                    }
                });
            },
            //不通过
            no(row,i){
                let _this = this;
                axios.get(this.url + 'updateLogoUnapply', {
                    params: {
                        cid:row.cid
                    }
                }).then(function (data) {
                    if (data.data === 1) {
                        _this.$message({
                            message: '操作成功',
                            type: 'success'
                        });
                        _this.onload();
                    } else {
                        _this.$message({
                            message: '操作失败',
                            type: 'error'
                        });
                    }
                });
            },
            formatter(row){
              if (row.isEnable===1){
                  return '启用'
              }else {
                  return '禁用'
              }
            },

        }
    }
</script>
<style scoped>
    .page7 {
        width: 100%;
        height: 800px;
        background-size: 100% 100%;
        overflow: hidden;
    }

    h1 {
        margin-left: -1400px;
        font-size: 40px;
    }

    .box-card {
        background-color: transparent !important;
    }

</style>