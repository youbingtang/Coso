<template>
    <div class="login ">
        <el-form :model="ruleForm2" :rules="rules2"
                 status-icon
                 ref="ruleForm2"
                 label-position="right"
                 label-width="123px"
                 class="demo-ruleForm login-zhuce"
        >
            <h3>注册企业</h3>
            <hr>
            <el-form-item prop="username" label="企业名称" class="redItem1">
                <el-input v-model="ruleForm2.username" placeholder="请输入企业名称"></el-input>
            </el-form-item>
            <el-form-item prop="companyType" label="公司类型" class="redItem1">
                <el-select v-model="ruleForm2.companyType" placeholder="请选择公司类型" style="width: 100%">
                    <el-option
                            v-for="item in ruleForm2.options1"
                            :key="item.value"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item prop="provinceId" label="注册省份" class="redItem1">
                <el-select v-model="ruleForm2.provinceId" placeholder="请选择所在省份" style="width: 100%"
                           @change="findcity(ruleForm2.provinceId)">
                    <el-option
                            v-for="item in ruleForm2.options2"
                            :key="item.value"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item prop="cityId" label="注册城市" class="redItem1">
                <el-select v-model="ruleForm2.cityId" placeholder="请选择所在城市" style="width:100%">
                    <el-option
                            v-for="item in ruleForm2.options3"
                            :key="item.value"
                            :label="item.name"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item prop="creditCode" label="组织机构代码" class="redItem1">
                <el-input v-model="ruleForm2.creditCode" placeholder="请输入组织机构代码"></el-input>
            </el-form-item>
            <el-form-item prop="cerificateAttachmen" label="企业信用代码" class="redItem1">
                <el-input v-model="ruleForm2.cerificateAttachmen" placeholder="请输入企业信用代码"></el-input>
            </el-form-item>
            <el-form-item prop="admin" label="管理员账号" class="redItem1">
                <el-input v-model="ruleForm2.admin" placeholder="请输入管理员账号"></el-input>
            </el-form-item>
            <el-form-item prop="email" label="联系邮箱" class="redItem1">
                <el-input v-model="ruleForm2.email" placeholder="请输入邮箱"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon @click="doRegister('ruleForm2')" style="margin-left: 0px;width: 40%">
                    注册账号
                </el-button>
                <el-button type="primary" icon @click="doGet" style="margin-left: 70px;width: 40%">
                    返回
                </el-button>
            </el-form-item>
        </el-form>
        <div class="footer">
            <p class="three"><strong>中国远洋海运集团有限公司</strong><strong> 版权所有</strong><strong> 电话：</strong><strong>
                021-65966666 </strong><strong> 技术支持单位：</strong><strong>中远海运科技股份有限公司</strong></p>
            <p class="four" style="margin-top: -10px"><strong>沪ICP备16018315号</strong><strong> 沪公网安备
                31022102000135号</strong></p>
            <div class="five" style="margin-top: -10px">网站地图<span> | </span>联系我们<span> | </span>法律声明</div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "Register",
        data() {
            var validatePass = (rule, value, callback) => {
                var req = /^[\u4e00-\u9fa5]+$/;
                if (!value) {
                    return callback(new Error("请输入企业名称"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是中文"));
                } else if (value.toString().length > 12) {
                    return callback(new Error("长度为小于12位"));
                } else {
                    return callback();
                }
            };
            var validatePass1 = (rule, value, callback) => {
                var req = /^[0-9a-zA-Z_]*$/;
                if (!value) {
                    return callback(new Error("请输入组织机构代码"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是数字和字母"));
                } else if (value.toString().length < 9 || value.toString().length > 17) {
                    return callback(new Error("长度在9到17位之间"));
                } else {
                    return callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                var req = /^[0-9a-zA-Z_]*$/;
                if (!value) {
                    return callback(new Error("请输入企业信用代码"));
                } else if (!req.test(value)) {
                    return callback(new Error("只能是数字和字母"));
                } else if (value.toString().length < 18 || value.toString().length > 18) {
                    return callback(new Error("长度只能是18位"));
                } else {
                    return callback();
                }
            };
            var validatePass3 = (rule, value, callback) => {
                var reg = /^1(3|4|5|6|7|8|9)\d{9}$/;
                if (!value) {
                    return callback(new Error("请输入管理员账户"));
                } else if (!reg.test(value)) {
                    return callback(new Error("手机号格式错误"));
                } else if (value.toString().length < 11 || value.toString().length > 11) {
                    return callback(new Error("管理员账户长度为11位"));
                } else {
                    return callback();
                }
            };
            var validatePass4 = (rule, value, callback) => {
                var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if (!value) {
                    return callback(new Error("请输入电子邮箱名称"));
                } else if (!reg.test(value)) {
                    return callback(new Error("请输入正确格式"));
                } else {
                    return callback();
                }
            };
            return {
                url:'http://localhost:8888/Cosoproject/',
                ruleForm2: {
                    username: "",
                    companyType: "",
                    provinceId: "",
                    cityId: "",
                    options1: [],
                    options2: [],
                    options3: [],
                    creditCode: "",
                    cerificateAttachmen: "",
                    email: "",
                    admin: ""
                },
                rules2: {
                    username: [{required: true, validator: validatePass, trigger: 'blur'}],
                    companyType: [{required: true, message: '请选择', trigger: 'blur'}],
                    provinceId: [{required: true, message: '请选择', trigger: 'blur'}],
                    cityId: [{required: true, message: '请选择', trigger: 'blur'}],
                    creditCode: [{required: true, validator: validatePass1, trigger: 'blur'}],
                    cerificateAttachmen: [{required: true, validator: validatePass2, trigger: 'blur'}],
                    admin: [{required: true, validator: validatePass3, trigger: 'blur'}],
                    email: [{required: true, validator: validatePass4, trigger: 'blur'}],
                }


            };
        },
        created() {
            let _this = this;
            axios.get(this.url+'findProvince')
                .then(function (data) {
                    _this.ruleForm2.options2 = data.data;
                });
            axios.get(this.url+'findAllCompanyType')
                .then(function (data) {
                    _this.ruleForm2.options1 = data.data;
                })
        },
        methods: {
            findcity(id) {
                let _this = this;
                axios.get(this.url+'findCity',
                    {
                        params: {
                            provinceId: id
                        }
                    }).then(function (data) {
                    _this.ruleForm2.options3 = data.data;
                })
            },
            doGet(){
                this.$router.push({path: '/'})
            },
            doRegister(ruleForm2) {
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get(this.url+'insertCompany', {
                            params: {
                                name: this.ruleForm2.username,
                                companyTypeId: this.ruleForm2.companyType,
                                provinceId: this.ruleForm2.provinceId,
                                cityId: this.ruleForm2.cityId,
                                creditCode: this.ruleForm2.creditCode,
                                cerificateAttachment: this.ruleForm2.cerificateAttachmen,
                                account: this.ruleForm2.admin,
                                email: this.ruleForm2.email,
                            }
                        }).then(function (data) {
                            console.log(data);
                            if (data.data === 1) {
                                _this.$message('注册成功');
                                _this.$router.push({path: '/'})
                            }else if(data.data===-1){
                                _this.$message('未知异常');
                            }else if(data.data===2){
                                _this.$message('账号已存在');
                            }
                        });
                    } else {
                        console.log("error");
                        return false;
                    }
                });
            }
        }
    };
</script>

<style>
    .login {
        width: 100%;
        height: 100%;
        background: url("../assets/2.jpg") no-repeat;
        background-size: 100% 100%;
        overflow: hidden;
    }

    .login-zhuce {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 100px 10px 10px 200px;
        width: 500px;
        padding: 35px 35px 15px;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    h3 {
        color: white;
        font-size: 24px;
        text-align: center;
    }

    hr {
        background-color: #444;
        margin: 20px auto;
    }

    .redItem1 .el-form-item__label {
        color: white;
    }

    .footer {
        color: rosybrown;
        margin: 55px 10px 10px 200px;
    }

</style>