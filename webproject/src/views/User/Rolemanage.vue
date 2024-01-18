//角色管理
<template>
    <div class="page65">
        <el-form :model="ruleForm2"
                 status-icon
                 ref="ruleForm2"
                 label-position="left"
                 class="demo-ruleForm2 ">
            <h1>角色管理</h1>
            <el-card class="box-card">
                <el-table
                        :data="tableData.list"
                        :header-cell-style="{textAlign:'center'}"
                        :cell-style="{ textAlign: 'center' }"
                        style="width: 52%;margin-left:25% "
                        selection-change="handleSelectionChange"
                >
                    <el-table-column prop="createDate" label="建创时间" width="280px">
                    </el-table-column>
                    <el-table-column prop="name" label="角色名称" width="280px">
                    </el-table-column>
                    <el-table-column prop="phone" label="" width="280">
                        <template slot-scope="scope">
                            <el-button type="primary" icon="el-icon-edit" @click="revise(scope.row,scope.$index)">修改
                            </el-button>
                            <el-button type="danger" icon="el-icon-delete" @click="del(scope.row,scope.$index)">删除
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
                                    <td>
                                        <el-button type="primary" icon="el-icon-arrow-left" style="float: left"
                                                   @click="sub()">上一页
                                        </el-button>
                                    </td>
                                    <td>第{{tableData.pageNum}}页</td>
                                    <td>
                                        <el-button type="primary" style="float: right" @click="add()">下一页<i
                                                class="el-icon-arrow-right el-icon--right"></i></el-button>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </el-col>
                </el-row>
                <el-button style="width:10%;margin-left:-900px" type="primary" @click="toAmend()">+添加角色</el-button>
            </el-card>
        </el-form>

        <!--添加角色-->
        <el-dialog title="添加角色" :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <el-form ref="data" :model="data" :rules="rule" label-width="80px" >
                <el-form-item label="角色名称" prop="name" style="width: 380px;margin-left: 28%">
                    <el-input v-model="data.name"></el-input>
                </el-form-item>
                    <el-divider><i class="el-icon-s-custom"></i></el-divider>
                <el-form-item style="text-align: left">
                    角色权限：<el-checkbox-group
                            v-model="data.checkList"
                            :max="7">
                        <el-checkbox v-for="(v,i) in checkList" :label="checkList[i].id">{{checkList[i].name}}</el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="honsen('data')">确 定</el-button>
            </div>
        </el-dialog>

        <!--修改角色权限-->
        <el-dialog title="修改角色" :visible.sync="dialog">
            <el-form ref="redata" :model="redata" :rules="rule" label-width="80px" >
                <el-form-item label="角色名称" prop="name" style="width: 380px;margin-left: 28%">
                    <el-input v-model="redata.name" :disabled="true"></el-input>
                </el-form-item>
                <el-divider><i class="el-icon-s-custom"></i></el-divider>
                <el-form-item style="text-align: left">
                    角色权限：<el-checkbox-group
                        v-model="redata.checkList"
                        :max="7">
                    <el-checkbox v-for="(v,i) in checkList" :label="checkList[i].id">{{checkList[i].name}}</el-checkbox>
                </el-checkbox-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="honson('redata')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    import * as qs from "qs";

    export default {
        data() {
            let name = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入角色名称"));
                } else {
                    callback();
                }
            };
            return {
                url: "http://localhost:8888/Cosoproject/",
                tableData: [], //存放从后端传来的数据
                currentPage: 1, //当前页数
                dialogFormVisible:false,
                dialog:false,
                data:{
                    name:'',
                    checkList:[29],
                },
                redata:{
                    name:'',
                    checkList:[],
                },
                checkList:{},
                ruleForm2: {},
                rule: {
                    name: [
                        { required: true,validator:name , trigger: 'blur' }
                    ],
                },
                rowid:'',
            }
        },
        created() {
            this.onload();
        },
        methods: {
            quxiao(){
                this.dialog=false;
                this.dialogFormVisible=false;
                this.data.name='';
                this.data.checkList=[29];
                this.redata.name='';
                this.redata.checkList=[];
            },
            onload() {
                let _this = this;
                axios.get(this.url + 'findAllRole', {
                    params: {
                        cid: sessionStorage.getItem("companyid"),
                        type:'2',
                        currentPage: this.currentPage
                    }
                }).then(function (data) {
                    _this.tableData = data.data;
                })
            },
            sub() {
                if (this.tableData.hasPreviousPage) {
                    this.currentPage--;
                    this.onload();
                }
            },
            add() {
                if (this.tableData.hasNextPage) {
                    this.currentPage++;
                    this.onload();
                }
            },
            toAmend(){
                this.dialogFormVisible=true;
                let _this=this;
                axios.get(this.url+'findrole?cid='+sessionStorage.getItem("companyid"))
                    .then(function (data) {
                        _this.checkList=data.data
                    })
            },
            //添加确认
            honsen(data){
                this.$refs[data].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get(this.url+'addroleauthority', {
                            params: {
                                name:_this.data.name,
                                account:sessionStorage.getItem("account"),
                                authorityId:_this.data.checkList
                            },
                            paramsSerializer:params => {
                                return qs.stringify(params,{indices:false})
                            }
                        }).then(function (data) {
                            if(data.data===1){
                                _this.$message({
                                    message: '添加成功',
                                    type: 'success'
                                });
                                _this.onload();
                                _this.dialogFormVisible=false;
                            }else {
                                _this.$message({
                                    message: '添加出现异常请联系管理员',
                                    type: 'error'
                                });
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            //修改确认
            honson(redata){
                this.$refs[redata].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get(this.url+'updateroleauthority', {
                            params: {
                                id:_this.rowid,
                                authorityId:_this.redata.checkList
                            },
                            paramsSerializer:params => {
                                return qs.stringify(params,{indices:false})
                            }
                        }).then(function (data) {
                            if(data.data===1){
                                _this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                _this.onload();
                            }else {
                                _this.$message({
                                    message: '修改出现异常请联系管理员',
                                    type: 'error'
                                });
                            }
                            _this.dialog=false;
                        });
                    } else {
                        return false;
                    }
                });
            },
            //删除
            del(row,index){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    axios.get(this.url+'delrole?id='+row.id)
                        .then(function (data) {
                            if(data.data===1){
                                _this.$message({
                                    message: '删除成功',
                                    type: 'success'
                                });
                                _this.onload();
                            }else {
                                _this.$message({
                                    message: '删除发生异常，请联系管理员',
                                    type: 'error'
                                });
                            }
                        });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //修改
            revise(row,i){
                this.rowid=row.id;
                let _this=this;
                this.redata.name=row.name;
                axios.get(this.url+'findrole?cid='+sessionStorage.getItem("companyid"))
                    .then(function (data) {
                        _this.checkList=data.data
                    });
                axios.get(this.url+'findauthoritybyroid?roleId='+row.id)
                    .then(function (data) {
                        for (let i=0; i < data.data.length; i++) {
                            _this.redata.checkList.push(data.data[i].id);
                        }
                    });
                this.dialog=true;
            },
        }
    }
</script>
<style scoped>
    .page65 {
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