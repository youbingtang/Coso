<template>
    <div class="login-container">
        <el-form :model="ruleForm2" :rules="rules2"
                 ref="ruleForm2"
                 label-position="right"
                 label-width="80px"
                 class="demo-ruleForm login-page"
        >
            <el-form-item prop="username"label="账户：" class="redItem">
                <el-input type="text"
                          v-model="ruleForm2.username"
                          auto-complete="off"
                          placeholder="手机号"
                ></el-input>
            </el-form-item>
            <el-form-item prop="password"label="密码："class="redItem">
                <el-input type="password"
                          v-model="ruleForm2.password"
                          auto-complete="off"
                          placeholder="密码"
                ></el-input>
            </el-form-item>
            <el-form-item prop="phone"label="验证码："class="redItem">
                <el-input type="text"
                          v-model="ruleForm2.phone"
                          auto-complete="off"
                          placeholder="验证码"
                          style="width:70%;"
                ></el-input>
                <div @click="handleRefreshCode()" style="width: 30px;float: right;margin-right: 22%;margin-left: 2px">
                    <Identify :identifyCode="identifyCode"></Identify>
                </div>
            </el-form-item>
            <el-row style="text-align: center; margin-top: -10px;">
                <el-link type="primary"style="float: right;color:white"@click="doRegister()">企业注册</el-link>
            </el-row>
            <el-form-item style="width:100%;"prop="aaa">
                <el-button prop="aaa" type="primary" style="width:100%;" @click="handleSubmit('ruleForm2')" :loading="logining">登录</el-button>
            </el-form-item>
        </el-form>
        <div class="footer1">
            <p class="three"><strong >中国远洋海运集团有限公司</strong><strong >  版权所有</strong><strong >  电话：</strong><strong> 021-65966666  </strong><strong> 技术支持单位：</strong><strong>中远海运科技股份有限公司</strong></p>
            <p class="four"style="margin-top: -10px"><strong>沪ICP备16018315号</strong><strong >       沪公网安备 31022102000135号</strong></p>
            <div class="five"style="margin-top: -10px">网站地图<span> | </span>联系我们<span> | </span>法律声明</div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import Identify from '../components/Identity.vue'
    export default {
        name: "Login",
        components: { Identify },
        data() {
            var validatePass = (rule, value, callback) => {
                var reg = /^1(3|4|5|6|7|8|9)\d{9}$/
                if (!value) {
                    callback(new Error("请输入手机号"));
                } else if (!reg.test(value)) {
                    callback(new Error("手机号格式错误"));
                } else if (value.toString().length < 11 || value.toString().length > 11) {
                    callback(new Error("手机号长度为11位"));
                } else {
                    callback();
                }
            };
            var validatePass1 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入密码"));
                } else if (value.toString().length > 11) {
                    callback(new Error("密码长度为小于11位"));
                } else {
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (!value) {
                    callback(new Error("请输入验证码"));
                } else if (value.toString() !== this.identifyCode) {
                    callback(new Error("验证码错误"));
                } else {
                    callback();
                }
            };
            return {
                logining: false,
                identifyCode:'',
                identifyCodes: '1234567890abcdefghijklmnopqrstuvwxyz',
                ruleForm2: {
                    username: '',
                    password: '',
                    phone: '',
                },

                rules2: {
                    username: [{required: true, validator: validatePass, trigger: 'blur'}],
                    password: [{required: true, validator: validatePass1, trigger: 'blur'}],
                    phone: [{required: true, validator: validatePass2, trigger: 'blur'}]
                },
            }

        },
        mounted(){
          this.handleRefreshCode();
        },
        methods: {
            handleSubmit(ruleForm2) {
                this.$refs[ruleForm2].validate((valid) => {
                    if (valid) {
                        let _this = this;
                        axios.get('http://localhost:8888/Cosoproject/login',
                            {
                                params: {
                                    account: this.ruleForm2.username,
                                    password: this.ruleForm2.password,
                                }
                            }).then(function (data) {
                            if (data.data===4){
                                sessionStorage.setItem("account",_this.ruleForm2.username);
                                sessionStorage.setItem("password",_this.ruleForm2.password);
                                _this.$router.push({path:'/Index'});
                            } else if (data.data===0) {
                                _this.$message.error('用户名不存在');
                            } else if (data.data===1) {
                                _this.$message.error('密码错误');
                            } else if (data.data===2) {
                                _this.$message.error('账号已锁定');
                            } else if (data.data===3) {
                                _this.$message.error('用户已删除');
                            }
                        })
                    } else {
                        _this.handleRefreshCode();
                        return false;
                    }
                });
            },
            handleRefreshCode() {
                this.identifyCode = '';
                this.makeCode(this.identifyCodes, 4)
            },
            // 生成一个随机整数  randomNum(0, 10) 0 到 10 的随机整数， 包含 0 和 10
            randomNum(min, max) {
                max = max + 1;
                return Math.floor(Math.random() * (max - min) + min)
            },
            // 随机生成验证码字符串
            makeCode(data, len) {
                for (let i = 0; i < len; i++) {
                    this.identifyCode += data[this.randomNum(0, data.length - 1)]
                }
            },
            doRegister() {
                this.$router.push({path: '/Register'});
            },
        },

    }
</script>

<style >
    .login-container {
        width: 100%;
        height: 100%;
        background: url("../assets/2.jpg") no-repeat;
        background-size: 100% 100%;
        overflow: hidden;
    }
    .login-page {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 200px 10px 10px 200px;
        width: 500px;
        padding: 100px 35px 15px;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .redItem .el-form-item__label{
        color:white;
    }
    .footer1{
        color: rosybrown;
        margin:279px 10px 10px 200px;
    }

</style>