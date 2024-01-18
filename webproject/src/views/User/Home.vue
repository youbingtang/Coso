<template>
    <div v-if="isShow===0">
        <h3>欢迎使用！</h3>
        <h2>本公司还未开通任何功能，请: <router-link to="/Index1/busapply">开通</router-link></h2>
    </div>
    <div v-else-if="isShow===1">
        <el-card class="box-card" v-if="send===1">
            <h4>出单数据：</h4><br/><br/><br/>
            <h5>公司出单数：{{data.sendcount}}</h5>
            <h5>待出单审核数：{{data.sendsccount}}</h5>
        </el-card>
        <el-card class="box-card" v-if="receive===1">
            <h4>收单数据：</h4><br/><br/><br/>
            <h5>公司收单数：{{data.receivecount}}</h5>
            <h5>本月新增收单数：{{data.receivercfcount}}</h5>
        </el-card>
        <el-card class="box-card" v-if="signature===1">
            <h4>签章数据：</h4><br/><br/><br/>
            <h5>公司签章提单数：{{data.signaturecount}}</h5>
            <h5>待签章审核数：{{data.signaturesigcount}}</h5>
        </el-card>
    </div>
</template>
<script type="text/ecmascript-6">
    import axios from 'axios'
    export default {
        created(){
            this.onload();
            this.selectdata();
        },
        data() {
            return {
                url:'http://localhost:8888/Cosoproject/',
                data:{},
                isShow:1,
                send:0,
                receive:0,
                signature:0,
            }
        },
        methods:{
            onload(){
                let _this=this;
                axios.get(this.url+'findAllCompanyInfor',{
                    params:{
                        cid:sessionStorage.getItem("companyid")
                    }
                }).then((data)=>{
                    if (data.data.isSendBl === 0 && data.data.isReceive === 0 && data.data.isSignature === 0) {
                        _this.isShow=0;
                    }else {
                        _this.isShow=1;
                        _this.send=data.data.isSendBl;
                        _this.receive=data.data.isReceive;
                        _this.signature=data.data.isSignature;
                    }
                });
            },
            selectdata(){
                let _this=this;
                axios.get(this.url+"findCompanyBill?id="+sessionStorage.getItem("companyid"))
                    .then(function (data) {
                        _this.data=data.data;
                    })
            },

        }
    }
</script>

<style scoped>
    h3{
        margin-left: -1200px;
        font-size: 50px;
        color: black;
    }
    h2{
        margin-left: -1070px;
    }
    h4{
        margin-left: -1000px;
        font-size: 30px;
        display: inline;
    }
    h5{
        display: inline;
        font-size: 25px;
        margin-right: 200px;
        margin-left: 100px;
    }
    .box-card{
        background-color: transparent !important;
        margin-top: 80px;
        height: 160px;
        margin-left: 100px;
        margin-right: 100px;
    }

</style>