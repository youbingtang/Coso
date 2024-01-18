
<template>
    <div style="margin-top:50px ">
        <table width="1200px" style="height: 600px;FILTER:alpha(40)">
            <tbody>
            <tr><td>企业名称：</td><td>{{tableData.name}}</td><td>企业类型：</td><td>{{tableData.companyType}}</td></tr>
            <tr><td>企业状态：</td><td>{{status}}</td><td>联系邮箱：</td><td>{{tableData.email}}</td></tr>
            <tr><td>注册省份：</td><td>{{tableData.province}}</td><td>注册城市：</td><td>{{tableData.city}}</td></tr>
            <tr><td>组织机构代码证：</td><td>{{tableData.cerificateAttachment}}</td><td>企业信用代码</td><td>{{tableData.creditCode}}</td></tr>
            <tr><td>创建时间：</td><td>{{tableData.createDate}}</td><td>创建人账号：</td><td>{{tableData.name}}</td></tr>
            <tr><td>创建人姓名：</td><td>{{tableData.modifyUserName}}</td><td>企业管理员账号：</td><td>{{tableData.adminAccount}}</td></tr>
            <tr><td>是否开通出单服务：</td><td>{{(tableData.isSendBl===1?'是':'否')}}</td><td>是否开通收单服务：</td><td>{{(tableData.isReceive===1?'是':'否')}}</td></tr>
            <tr><td>是否开通签章服务：</td><td colspan="3">{{tableData.isSignature===1?'是':'否'}}</td></tr>
            </tbody>
        </table>
        <el-button type="primary" @click="update()"style="width: 10%;margin-top: 60px ">修  改</el-button>
        <el-dialog title="修改企业信息" :visible.sync="dialogFormVisible"
                   :close-on-click-modal="false"
                   :close-on-press-escape="false"
                   :show-close="false">
            <el-col :span="24" class="col1">
                <div class="ios"style="margin-right: 300px">
                    <p align="right">企业名称：<el-input type="text" v-model="detailsData.name" auto-complete="off" style="width: 240px" ></el-input></p>
                    <p align="right">企业类型：<el-select v-model="detailsData.companyTypeId" placeholder="请选择公司类型" style="width: 240px">
                        <el-option
                                v-for="item in options1"
                                :key="item.value"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select></p>
                    <p align="right">企业邮箱：<el-input type="text" v-model="detailsData.email" auto-complete="off" style="width: 240px" ></el-input></p>
                    <p  align="right">管理员姓名：<el-input type="text" v-model="detailsData.createUserName" auto-complete="off" style="width: 240px" ></el-input></p>
                </div>
            </el-col>
            <div slot="footer" class="dialog-footer">
                <el-button  type="primary" @click="quxiao()">取 消</el-button>
                <el-button type="primary" @click="hanson()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Cominfomanage",
        data(){
            return{
                url:"http://localhost:8888/Cosoproject/",
                tableData:{}, //存放从后端传来的数据
                detailsData:{},
                options1:[],
                status:'',
                dialogFormVisible:false,

            }
        },
        created(){
            let _this=this;
            axios.get(this.url+'findAllCompanyInfor',{
                params:{
                    cid:sessionStorage.getItem('companyid'),
                }
            }).then(function (data) {
               _this.tableData=data.data;
               if(data.data.status===1){
                   _this.status='通过';
               }else if(data.data.status===0){
                   _this.status='未审核'
               }else if (data.data.status===2){
                   _this.status='未通过'
               }
            });
            axios.get(this.url+'findAllCompanyType')
                .then(function (data) {
                    _this.options1 = data.data;
                })
        },
        methods:{
           //修改
            update(){
              this.dialogFormVisible=true;
              this.detailsData=this.tableData
            },
            //确认
            hanson(){
                let _this=this;
                if(!this.detailsData.name){
                    _this.$message.error('请输入名称');
                }else if(!this.detailsData.companyTypeId){
                    _this.$message.error('请选择名称');
                }else if(!this.detailsData.email){
                    _this.$message.error('请输入邮箱');
                }else if (!this.detailsData.createUserName) {
                    _this.$message.error('请输入管理员姓名');
                }else{
               axios.get(this.url+'updateCompanyInfor',{
                   params:{
                       account:sessionStorage.getItem('account'),
                       name:this.detailsData.name,
                       companyTypeId:this.detailsData.companyTypeId,
                       email:this.detailsData.email,
                       createUserName:this.detailsData.createUserName
                   }
               }).then(function (data) {
                   if (data.data===1){
                       _this.$message({
                                   message: '修改成功',
                                   type: 'success'});
                       _this.dialogFormVisible=false;
                       axios.get(_this.url+'findAllCompanyInfor',{
                           params:{
                               cid:sessionStorage.getItem('companyid'),
                           }
                       }).then(function (data) {
                           _this.tableData=data.data;
                           if(data.data.status===1){
                               _this.status='通过';
                           }else if(data.data.status===0){
                               _this.status='未审核'
                           }else if (data.data.status===2){
                               _this.status='未通过'
                           }
                       });
                   } else {
                       _this.$message.error('操作失败')
                   }
               })
            }
            },
            //取消
            quxiao(){
             this.dialogFormVisible=false;
             this.detailsData={};
            }

        }
    }
</script>

<style scoped>
    table
    {
        border-collapse: collapse;
        margin: 0 auto;
        text-align: center;
    }
    table td, table th
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
    table tr:nth-child(odd)
    {
        background: #fff;
    }
    table tr:nth-child(even)
    {
        background: #F5FAFA;
    }
</style>