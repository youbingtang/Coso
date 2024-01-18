<template>
    <el-container class="wide" style="height: 100%">
        <el-header>
            <div style="float: left">
                <h1 style="margin-left:30px">{{show[0].cname}}</h1>
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
                <h4 style="color: black;float: left">{{show[0].rname}}：{{show[0].name}}</h4>
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
                    <el-menu-item index="/Index1/home1" >
                        <i class="el-icon-s-home"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>

                    <el-menu-item :index="'/Index1'+v.url" v-for="(v,i) in menuList" v-if="!v.childrenlist">
                        <i :class="v.icon"></i>
                        <span slot="title">{{v.showText}}</span>
                    </el-menu-item>

                    <el-submenu :index="'/Index1'+item.url" v-if="item.childrenlist" v-for="item in menuList" :key="item.id">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span>{{item.showText}}</span>
                        </template>
                        <el-menu-item v-for="subItem in item.childrenlist" :key="subItem.id" :index="'/Index1'+subItem.url">
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
        name: "Index1",
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
                // this.$router.push({path:'/Index/datev'})
                window.open('http://localhost:8080/Index1/datev','_blank','width=2000,height=990,top=10,left=0');
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
                    sessionStorage.setItem("companyid",data.data.list[0].cid);
                    sessionStorage.setItem("name",data.data.list[0].name);
                    sessionStorage.setItem("cname",data.data.list[0].cname);
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