<template>
    <div class="Upage5">
        <h1 >logo管理</h1>
        <div class="login-wrap4">
            <el-form
                    :model="resetForm"
                    :rules="resetFormRules"
                    ref="resetForm"
                    status-icon
                    label-width="150px"
            >
                <el-form-item label="logo名称：" prop="logoname">
                    <el-input type="text" v-model="resetForm.logoname" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="logo源文件：" prop="logofile">
                    <el-image :src="src+resetForm.logofile" style="width: 150px;height: 150px;">
                        <div slot="placeholder" class="image-slot">
                            加载中<span class="dot">...</span>
                        </div>
                        <div slot="error" class="image-slot" style="margin-top: 80px">
                            暂无logo信息
                        </div>
                    </el-image>
                </el-form-item>
                <el-form-item label="启用状态：" prop="status">
                    {{status}}
                </el-form-item>
                <el-button style="width:30%;margin-left:150px" type="primary" @click="xiugai()" :disabled="btnshow===0">修改</el-button>
                <el-button style="width:30%;margin-left: 40px" type="primary" @click="upload()" :disabled="btnshow===1">上传</el-button>
            </el-form>
        </div>

        <!--//上传弹框-->
        <el-dialog
                title="Logo上传"
                :visible.sync="centerDialogVisible"
                width="30%"
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
                    <el-form-item label="logo名称：" prop="logoname">
                        <el-input type="text" v-model="resetForm1.logoname" auto-complete="off" style="width: 250px;"></el-input>
                    </el-form-item>
                    <el-form-item label="logo文件：" prop="logofile">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                :data="resetForm1"
                                action="http://localhost:8888/Cosoproject/insertSendBlLogo"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                list-type="picture-card"
                                :auto-upload="false">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="上传日期：">
                        <el-input type="text" v-model="resetForm.time" auto-complete="off" style="width: 250px;" :disabled="true"></el-input>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="quxiao()">取 消</el-button>
            <el-button type="primary" @click="submitUpload()">确 定</el-button>
            </span>
        </el-dialog>

        <!--修改logo-->
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                center
                :close-on-click-modal="false"
                :close-on-press-escape="false"
                :show-close="false">
            <span>
                <el-form
                        :model="resetForm2"
                        :rules="resetFormRules"
                        ref="resetForm"
                        status-icon
                        label-width="150px"
                >
                    <el-form-item label="logo名称：" prop="logoname">
                        <el-input type="text" v-model="resetForm2.logoname" auto-complete="off" style="width: 250px;"></el-input>
                    </el-form-item>
                    <el-form-item label="logo文件：" prop="logofile">
                        <el-upload
                                class="upload-demo"
                                ref="upload"
                                :data="resetForm2"
                                action="http://localhost:8888/Cosoproject/updateBlLogo"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                list-type="picture-card"
                                :auto-upload="false">
                            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        </el-upload>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="quxiao()">取 消</el-button>
            <el-button type="primary" @click="updateUpload()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: "Upage5",
        data() {
            var validatePass4 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入logo名称"));
                } else {
                    callback();
                }
            };
            return {
                src:'http://localhost:8888/logo/',
                url:'http://localhost:8888/Cosoproject/',
                centerDialogVisible:false,
                dialogImageUrl: '',
                dialogVisible: false,
                status:'',
                btnshow:1,
                resetForm: {
                    //传给后台所需要的参数
                    time:'',
                    logoname:'',
                    logofile:'',
                    status:'',
                },
                resetForm1:{
                    account:'',
                    logoname:'',
                },
                resetForm2:{
                    logoname:'',
                    account:'',
                    cid:'',
                },
                resetFormRules: {
                    logoname: [
                        { required: true, validator: validatePass4, trigger: "blur" }
                    ],

                }
            };
        },
        created(){
            this.onload();
        },
        methods: {
            quxiao(){
              this.resetForm1.account='';
              this.resetForm1.logoname='';
              this.resetForm2.account='';
              this.resetForm2.cid='';
              this.resetForm2.logoname='';
              this.dialogVisible=false;
              this.centerDialogVisible=false;
            },
            onload(){
                let _this=this;
                axios.get(this.url+'selectCompanyLogo?cid='+sessionStorage.getItem("companyid"))
                    .then(function (data) {
                        if (data.data === "") {
                            _this.btnshow=0
                        }
                        _this.resetForm.logoname=data.data.logname;
                        _this.resetForm.logofile=data.data.logofile;
                        if (data.data.blconfirmStatus===0){
                            _this.status='未审核';
                        } else if (data.data.blconfirmStatus === 1) {
                            if (data.data.logoisenable === 1) {
                                _this.status='启用';
                            } else if (data.data.logoisenable === 0) {
                                _this.status='禁用';
                            }
                        }
                  });
            },
            upload(){
                this.centerDialogVisible=true;
                this.getdate();
            },
            getdate(){
                let aData = new Date();
                this.resetForm.time =
                    aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();
            },
            submitUpload() {
                if (this.resetForm1.logoname === '') {
                    this.$message({
                        message:'logo名称不能为空',
                        type:'error'
                    });
                }else {
                    this.resetForm1.account=sessionStorage.getItem("account");
                    this.$refs.upload.submit();
                    this.quxiao();
                    this.onload();
                }
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            xiugai(){
              this.dialogVisible=true;
                this.resetForm2.logoname=this.resetForm.logoname;
            },
            updateUpload(){
                if (this.resetForm2.logoname === '') {
                    this.$message({
                        message:'logo名称不能为空',
                        type:'error'
                    });
                }else {
                    this.onload();
                    this.resetForm2.account=sessionStorage.getItem("account");
                    this.resetForm2.cid=sessionStorage.getItem("companyid");
                    this.$refs.upload.submit();
                    this.dialogVisible=false;
                }
            },

        }
    }
</script>

<style scoped>
    .Upage5{
        width: 100%;
        height: 765px;
        background-size: 100% 100%;
        overflow: hidden;
    }

    .login-wrap4 {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 100px auto;
        width: 600px;
        padding: 35px 35px 15px;
    }
    h1{
        color: black;
        margin: 50px 10px 10px 50px;
    }


</style>