<template>
    <el-container class="wide" style="height: 100%">
        <el-header>
            <div style="float: left" >
                <img src="../assets/5.png">
            </div>
            <div style="float: right;text-align: center;margin-left: 30px;margin-top: 12px;margin-right: 15px">
                <el-dropdown split-button type="primary" icon="el-icon-star-off" @click="handleClick" @command="command()">
                    数据展板
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="a">
                            退出
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
            <div style="float: right;text-align: center">
                <h4 style="color: black;float: left">平台管理员：张三</h4>
            </div>



        </el-header>
        <el-container>
            <el-aside width="200px" style="background-color:#02A7F0;">
                <el-menu
                        :router="true"
                        :default-active=$route.path
                        background-color="#02A7F0"
                        text-color="#fff"
                        active-text-color="#1d3157"
                        height="100%"
                >
                    <el-menu-item index="/Index2/home" >
                        <i class="el-icon-s-home"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-menu-item index="/Index2/voidaudit" >
                        <i class="el-icon-s-platform"></i>
                        <span slot="title">作废审核</span>
                    </el-menu-item>
                    <el-submenu :index="'/Index2'+item.url" v-for="item in menuList" :key="item.id">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span>{{item.showText}}</span>
                        </template>
                        <el-menu-item v-for="subItem in item.childrenlist" :key="subItem.id" :index="'/Index2'+subItem.url">
                            <template slot="title">
                                <i :class="subItem.icon"></i>
                                <span>{{subItem.showText}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>

            <!--主题区域-->
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Index",
        data(){
            return{
                url:'http://localhost:8888/Cosoproject/',
                //侧边导航数组
                menuList:[],
                show:[],
            }
        },
        created() {
            this.getItemMenu();
        },
        methods:{
            command(){
                this.login();
            },
            handleClick() {
                // this.$router.push({path:'/datev'})
                window.open('http://localhost:8080/Index2/datev','_blank','width=2000,height=990,top=10,left=0');
            },
            getItemMenu(){
                let _this = this;
                axios.get(this.url+'findMenuByAccount', {
                    params:{
                        account:sessionStorage.getItem("account")
                    }
                }).then(function (data) {
                    _this.menuList=data.data.authoritylist;
                    _this.show=data.data.list;
                    // if (data.data.authoritylist[0].showText=="平台管理") {
                    //     _this.$router.push({path:'/Index/home'})
                    // }else {
                    //     _this.$router.push({path:'/Index/home1'});
                    // }
                })
            },
            login(){
                window.sessionStorage.clear();
                this.$router.push('/');
            },
        }
    }
</script>

<style scoped>
    .el-header{
        background: url("../assets/4.jpg") no-repeat;
        background-size: 100%;
    }
    .el-main{
        background: url("../assets/4.jpg") no-repeat ;
        background-size: 100% 100%;
    }
</style>