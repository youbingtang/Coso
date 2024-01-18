//港口管理
<template>
    <div class="page8">
        <h1>港口管理</h1>
        <el-card class="box-card">
            <el-table
                    :data="tableData.list"
                    :header-cell-style="{textAlign:'center'}"
                    :cell-style="{ textAlign: 'center' }"
                    style="width: 70%;margin-left:14% "
                    selection-change="handleSelectionChange"
            >
                <el-table-column prop="name" label="港口名称" width="140">
                </el-table-column>
                <el-table-column prop="eName" label="港口英文名" width="140">
                </el-table-column>
                <el-table-column prop="country" label="所在国家" width="140">
                </el-table-column>
                <el-table-column prop="sheng" label="所在省份" width="140">
                </el-table-column>
                <el-table-column prop="city" label="所在城市" width="140">
                </el-table-column>
                <el-table-column prop="sendPrice" label="出单费用" width="140">
                </el-table-column>
                <el-table-column   width="280">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="apply(scope.row,scope.$index)">修改
                        </el-button>
                        <el-button type="info" icon="el-icon-close" @click="rebut(scope.row,scope.$index)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-row :gutter="20">
                <el-col :span="7" :offset="12">
                    <div class="block " style="margin-left: -600px;margin-top: 20px">
                        <table style="padding-left: 26%;padding-top: 30px">
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
            <el-button type="primary" style="margin-left:-1100px" @click="handleInsert">+添加新港口</el-button>
        </el-card>

        <!--修改按钮的弹框-->
        <el-dialog title="修改港口信息" :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <el-col :span="24" class="col1">
                <div class="ios" style="margin-right: 250px">
                    <el-form ref="detailsData" :model="detailsData" :rules="rule" label-width="80px" style="margin-left: 200px">
                        <el-form-item label="港口名称" prop="name">
                            <el-input v-model="detailsData.name"></el-input>
                        </el-form-item>
                        <el-form-item label="英文名称" prop="eName">
                            <el-input v-model="detailsData.eName"></el-input>
                        </el-form-item>
                        <el-form-item label="邮寄费用" prop="sendPrice">
                            <el-input v-model="detailsData.sendPrice"></el-input>
                        </el-form-item>
                    </el-form>
                </div>
            </el-col>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="submitxiugai('detailsData')">确 定</el-button>
            </div>
        </el-dialog>

        <!--添加按钮的弹框-->
        <el-dialog title="添加港口" :visible.sync="dialogFormVisible1">
            <el-col :span="24" class="col1">
                <div class="ios" style="margin-right: 250px">
                <el-form ref="data" :model="data" :rules="rule" label-width="80px" style="margin-left: 200px">
                    <el-form-item label="港口名称" prop="name">
                        <el-input v-model="data.name"></el-input>
                    </el-form-item>
                    <el-form-item label="英文名称" prop="ename">
                        <el-input v-model="data.ename"></el-input>
                    </el-form-item>
                    <el-form-item label="邮寄费用" prop="sendPrice">
                        <el-input v-model="data.sendPrice" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="所在国家" prop="countryId">
                        <el-select v-model="data.countryId" placeholder="请选择" @change="findprovinve(data.countryId)" style="width: 390px">
                            <el-option
                                    v-for="item in data.options1"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="所在省份" prop="provinceId">
                        <el-select v-model="data.provinceId" placeholder="请选择"  @change="findcity(data.provinceId)" style="width: 390px">
                            <el-option
                                    v-for="item in data.options2"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="所在城市" prop="cityId">
                        <el-select v-model="data.cityId" placeholder="请选择" style="width: 390px" @change="getBaiduMapPoint($event)">
                            <el-option
                                    v-for="item in data.options3"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="经度" prop="longitude">
                        <el-input v-model="data.longitude" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="纬度" prop="latitude">
                        <el-input v-model="data.latitude" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间" prop="createDate">
                        <el-input type="date" v-model="data.createDate"></el-input>
                    </el-form-item>
                </el-form>
                </div>
            </el-col>
            <div slot="footer" class="dialog-footer">
                <el-button @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="handleSubmit('data')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    export default {
        data() {
            let name = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入港口名"));
                } else {
                    callback();
                }
            };
            let ename = (rule, value, callback) => {
                let reg=/[^a-zA-Z]/g;
                if (!value) {
                    callback(new Error("请输入名称"));
                }else {
                    callback();
                }
            };
            let fee = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入费用"));
                } else {
                    callback();
                }
            };
            let createDate = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请选择日期"));
                } else {
                    callback();
                }
            };
            return {
                url: "http://localhost:8888/Cosoproject/",
                tableData:[], //存放从后端传来的数据
                detailsData:{
                    name:'',
                    eName:'',
                    sendPrice:'',
                },
                data:{
                    name:'',
                    ename:'',
                    countryId:'',
                    provinceId:'',
                    cityId:'',
                    longitude:'',
                    latitude:'',
                    sendPrice:'',
                    createDate :'',
                    options1:[],
                    options2:[],
                    options3:[],
                },
                options1:[],
                options2:[],
                options3:[],
                rule:{
                    name: [
                        { required: true, validator: name, trigger: 'blur' }
                    ],
                    ename: [
                        { required: true, validator: ename, trigger: 'blur' }
                    ],
                    eName: [
                        { required: true, validator: ename, trigger: 'blur' }
                    ],
                    countryId: [
                        { required: true, message: "请选择", trigger: 'blur' }
                    ],
                    provinceId: [
                        { required: true, message: "请选择", trigger: 'blur' }
                    ],
                    cityId: [
                        { required: true, message: "请选择", trigger: 'blur' }
                    ],
                    createDate: [
                        { required: true, validator: createDate, trigger: 'blur' }
                    ],
                    sendPrice:[
                        { required: true, validator: fee, trigger: 'blur' }
                    ],

                },
                currentPage:1, //当前页数
                rowid:'',
                dialogFormVisible:false,
                dialogFormVisible1:false,
            }
        },
        created(){
            this.onload();
        },
        methods:{
            onload(){
                let _this=this;
                axios.get(this.url+'portListPage',{
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
            quxiao(){
                this.detailsData={};this.dialogFormVisible = false;
                this.dialogFormVisible1 = false;
                this.data. name='';
                this.data.ename='';
                this.data.countryId='';
                this.data.provinceId='';
                this.data. cityId='';
                this.data.  longitude='';
                this.data.  latitude='';
                this.data. sendPrice='';
                this.data. createDate ='';
                this.data. options1=[];
                this.data. options2=[];
                this.data. options3=[];
                },
            findprovinve(id){
                this.data.provinceId='';
                this.data.cityId='';
                let _this=this;
                axios.get(this.url+'findProvince2',{
                    params:{
                        pid2:id
                    }}).then(function (data) {
                    _this.data.options2=data.data;

                })
            },
            findcity(id){
                this.data.cityId='';
                let _this=this;
                axios.get(this.url+'findCity3',{
                    params:{
                        pid3:id
                    }}).then(function (data) {
                    _this.data.options3=data.data;

                })
            },
            //修改
            apply(row,index){
                this.rowid=row.id;
                this.dialogFormVisible=true;
                this.detailsData.name=this.tableData.list[index].name;
                this.detailsData.eName=this.tableData.list[index].eName;
                this.detailsData.sendPrice=this.tableData.list[index].sendPrice;
                let _this=this;
                axios.get(this.url+'findCountry1'
                ).then(function (data) {
                    _this.options1 = data.data;
                });
            },
            //修改里的确认
            submitxiugai(detailsData){
                this.$refs[detailsData].validate((valid) => {
                    if (valid) {
                        this.hanson();
                    } else {
                        return false;
                    }
                });
            },
            hanson(){
                this.dialogFormVisible=false;
                let _this=this;
                axios.get(this.url+'portEdit',{
                    params:{
                        id:this.rowid,
                        name:this.detailsData.name,
                        ename:this.detailsData.eName,
                        sendPrice:this.detailsData.sendPrice,
                    },
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '修改成功',
                            type: 'success'
                        });
                    _this.onload();
                    }
                });
            },
            //删除
            rebut(row,index){
                let _this=this;
                axios.get(this.url+'portdoDel',{
                    params:{
                        id:row.id,
                    }}).then(function (data) {
                    if(data.data ===1){
                        _this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        _this.onload();
                    }else{
                        _this.$message.error('操作失败');
                    }
                })
            },
            //添加
            handleInsert(){
                this.dialogFormVisible1=true;
                let _this=this;
                axios.get(this.url+'findCountry1')
                    .then(function (data) {
                        _this.data.options1 = data.data;
                    });
            },
            //添加里的确认
            handleSubmit(ruleForm2) {
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        this.hanben();
                    } else {
                        return false;
                    }
                });
            },
            hanben(){
                let _this=this;
                axios.get(this.url+'portAdd',{
                    params:{
                        name:this.data.name,
                        ename:this.data.ename,
                        countryId:this.data.countryId,
                        provinceId:this.data.provinceId,
                        cityId:this.data.cityId,
                        sendPrice:this.data.sendPrice,
                        longitude:this.data.longitude,
                        latitude:this.data.latitude,
                        createDate:this.data.createDate,
                    }
                }).then(function (data) {
                    if(data.data===1){
                        _this.$message({
                            message: '添加成功',
                            type: 'success'
                        });
                        _this.onload();
                    }else {
                        _this.$message({
                            message: '添加出现异常，请联系管理员',
                            type: 'error'
                        });
                        _this.onload();
                    }
                })

            },
            getBaiduMapPoint(item){
                let obj = {};
                let _this=this;
                obj = this.data.options3.find((i)=>{//这里的userList就是上面遍历的数据源
                    return item.value === item[i];//筛选出匹配数据
                });
                this.$jsonp('http://api.map.baidu.com/geocoding/v3/', {
                    address: obj.name,
                    output: 'json',
                    ak: "Gh0Zd8NtpMSnCgpgCSGjPUeTKcQZQo2T"
                }).then(json => {
                    // 得到我们想要的内容
                    _this.data.longitude=json.result.location.lng;
                    _this.data.latitude=json.result.location.lat;
                    console.log(json.result.location.lng);//经度
                    console.log(json.result.location.lat);//纬度
                }).catch(err => {
                    console.log(err)
                });
            },
        },


    }
</script>
<style scoped>
    .page8{
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