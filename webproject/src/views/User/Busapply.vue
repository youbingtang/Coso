<template>
    <div v-if="show===0">
        <h3>功能申请:</h3>
        <h2>出单功能申请：<el-button type="primary" :disabled="true" v-if="send===1">已开通 </el-button>
                          <el-button type="primary" :disabled="true" v-if="send===3">待审核 </el-button>
            <el-button type="primary" :disabled="true" v-if="send===4">企业审核中，请先完善个人信息</el-button>
                          <el-button type="primary" v-else-if="send===0" @click="sends1()">开通申请</el-button></h2>
        <h2>收单功能申请：<el-button type="primary" :disabled="true" v-if="receive===1">已开通 </el-button>
                          <el-button type="primary" :disabled="true" v-if="receive===3">待审核 </el-button>
            <el-button type="primary" :disabled="true" v-if="receive===4">企业审核中，请先完善个人信息</el-button>
                          <el-button type="primary" v-else-if="receive===0" @click="receiveapply()">开通申请</el-button></h2>
        <h2>签章功能申请：<el-button type="primary" :disabled="true" v-if="signature===1">已开通 </el-button>
                          <el-button type="primary" :disabled="true" v-if="signature===3">待审核 </el-button>
            <el-button type="primary" :disabled="true" v-if="signature===4">企业审核中，请先完善个人信息</el-button>
                          <el-button type="primary" v-else-if="signature===0" @click="sends2()">开通申请</el-button></h2>

        <!--出单方上传seal-->
        <el-dialog
                title="印章上传"
                :visible.sync="centerDialogVisible"
                width="40%"
                center
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false">
            <span>
                <el-form
                        :model="resetForm1"
                        :rules="resetFormRules"
                        ref="resetForm"
                        status-icon
                        label-width="150px"
                >
                    <el-form-item label="印章名称：" prop="sealname">
                        <el-input type="text" v-model="resetForm1.sealname" auto-complete="off" style="width: 250px;"></el-input>
                    </el-form-item>
                    <el-form-item label="印章文件：" >
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                :data="resetForm1"
                                action="http://localhost:8888/Cosoproject/insertSendBlSeal"
                                list-type="picture-card"
                                :auto-upload="false">
                            <el-button slot="trigger" size="small" type="primary" class="huoqubtn">选取文件</el-button>
                        </el-upload>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="quxiao()">取 消</el-button>
            <el-button type="primary" @click="submitt()">确 定</el-button>
            </span>
        </el-dialog>

        <!--签章方上传seal-->
        <el-dialog
                title="签章上传"
                :visible.sync="DialogVisible"
                width="30%"
                center
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false">
            <span>
                <el-form
                        :model="resetForm2"
                        :rules="resetFormRules"
                        ref="resetForm2"
                        status-icon
                        label-width="150px"
                >
                    <el-form-item label="签章名称：" prop="name">
                        <el-input type="text" v-model="resetForm2.name" auto-complete="off" style="width: 250px;"></el-input>
                    </el-form-item>
                    <el-form-item label="签章文件：" prop="sealfile">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                :data="resetForm2"
                                action="http://localhost:8888/Cosoproject/insertSignatureSeal"
                                list-type="picture-card"
                                :auto-upload="false">
                            <el-button slot="trigger" size="small" type="primary" class="huoqubtn">选取文件</el-button>
                        </el-upload>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="quxiao()">取 消</el-button>
            <el-button type="primary" @click="submitUpload()">确 定</el-button>
            </span>
        </el-dialog>
    </div>

    <div v-else-if="show===1">
        <h3>功能申请:</h3>
        <h2>出单功能申请：
            <el-button type="primary" :disabled="true" class="weikaitong">企业审核中，请先完善个人信息</el-button></h2>
        <h2>收单功能申请：
            <el-button type="primary" :disabled="true" class="weikaitong">企业审核中，请先完善个人信息</el-button></h2>
        <h2>签章功能申请：
            <el-button type="primary" :disabled="true" class="weikaitong">企业审核中，请先完善个人信息</el-button></h2>
    </div>


</template>

<script>
    import axios from 'axios'
    export default {
        created() {
            this.onload();
        },
        data(){
            let validatePass4 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入印章名称"));
                } else {
                    callback();
                }
            };
            let validatePass3 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入签章名称"));
                } else {
                    callback();
                }
            };
            return{
                centerDialogVisible:false,
                DialogVisible:false,
                url:'http://localhost:8888/Cosoproject/',
                send:'',
                receive:'',
                signature:'',
                show:0,
                resetForm1:{
                    account:'',
                    sealname:'',
                },
                resetForm2:{
                    account:'',
                    name:'',
                },
                resetFormRules: {
                    name: [
                        { required: true, validator: validatePass3, trigger: "blur" }
                    ],
                    sealname: [
                        { required: true, validator: validatePass4, trigger: "blur" }
                    ],

                }
            }
        },
        methods:{
            onload(){
                let _this=this;
                axios.get(this.url+"findAllCompanyInfor?cid="+sessionStorage.getItem("companyid"))
                    .then(function (data) {
                        if (data.data.isReceive === 1) {
                            _this.receive=1;
                        }else {
                            _this.receive=0;
                        }
                        if (data.data.isSendBl === 1) {
                            _this.send=1;
                        }else {
                            _this.send=0;
                        }
                        if (data.data.isSignature === 1) {
                            _this.signature=1;
                        }else {
                            _this.signature=0;
                        }
                        if (data.data.status===0) {
                            _this.show=1;
                        }
                    })
            },
            sends1(){
                this.centerDialogVisible=true;
            },
            sends2(){
                this.DialogVisible=true;
            },
            sendapply(){
                let _this = this;
                axios.get(this.url+'companyapplyB?account='+sessionStorage.getItem("account"))
                    .then(function (data) {
                        if (data.data === 1) {
                            _this.send=3;
                            _this.$message({
                                message: '申请服务成功',
                                type: 'success'
                            });
                        }else if (data.data === -1) {
                            _this.$message({
                                message: '申请服务出现未知异常，请与管理员联系',
                                type: 'error'
                            });
                        }
                    });
            },
            receiveapply(){
                let _this = this;
                axios.get(this.url+'companyapplyA?account='+sessionStorage.getItem("account"))
                    .then(function (data) {
                        if (data.data === 1) {
                            _this.receive=3;
                            _this.$message({
                                message: '申请服务成功',
                                type: 'success'
                            });
                        }else if (data.data === -1) {
                            _this.$message({
                                message: '申请服务出现未知异常，请与管理员联系',
                                type: 'error'
                            });
                        }
                    });
            },
            signatureapply(){
                let _this = this;
                axios.get(this.url+'companyapplyC?account='+sessionStorage.getItem("account"))
                    .then(function (data) {
                        if (data.data === 1) {
                            _this.signature=3;
                            _this.$message({
                                message: '申请服务成功',
                                type: 'success'
                            });
                        }else if (data.data === -1) {
                            _this.$message({
                                message: '申请服务出现未知异常，请与管理员联系',
                                type: 'error'
                            });
                        }
                    });
            },

            quxiao(){
              this.DialogVisible=false;
              this.resetForm2.name='';
              this.resetForm2.account='';
              this.centerDialogVisible=false;
            },
            //签章确认上传
            submitUpload(){
                if (this.resetForm2.name === '') {
                    this.$message({
                        message:'签章名称不能为空',
                        type:'error'
                    });
                }else {
                    this.resetForm2.account=sessionStorage.getItem("account");
                    this.$refs.upload.submit();
                    this.DialogVisible=false;
                    this.signatureapply();
                }
            },
            //出单方上传
            submitt(){
                if (this.resetForm1.sealname === '') {
                    this.$message({
                        message:'印章名称不能为空',
                        type:'error'
                    });
                }else {
                    this.resetForm1.account=sessionStorage.getItem("account");
                    this.$refs.upload.submit();
                    this.centerDialogVisible=false;
                    this.sendapply();
                }
            },
        },
    }
</script>

<style scoped>
    h3{
        margin-left: -1200px;
        font-size: 50px;
        margin-top: 100px;
        color: black;
    }
    h2{
        margin-left: -600px;
        margin-top: 100px;

    }
    .el-button{
        margin-left: 100px;
        width: 100px;
    }
    .huoqubtn{
        margin-left: 0;
        width:100px
    }
    .weikaitong{
        margin-left: 100px;
        width: 240px;
    }

</style>