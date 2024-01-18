<template>

</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return{
                url:'http://localhost:8888/Cosoproject/'
            }
        },
        name: "Kong",
        mounted(){
          this.update();
        },
        methods:{
            update(){
                const loading = this.$loading({
                    lock: true,
                    text: '正在努力加载中，请稍后',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                setTimeout(() => {
                    loading.close();
                }, 2000);
                let _this = this;
                axios.get(this.url+'printone',{
                          params:{
                              paystatus:'1',
                              blNo: sessionStorage.getItem("blNo"),
                              account: sessionStorage.getItem('account'),
                          }
                      }).then(function (data) {
                    console.log(data);
                    if (data.data == 0) {
                              _this.$router.push({path:'/Index1/printapply'});
                          }
                      });
            }
        }
    }
</script>

<style scoped>

</style>