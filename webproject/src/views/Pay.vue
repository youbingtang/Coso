<template>
    <div id="alipay">

    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Pay",
        mounted(){
            this.pay();
        },
        created(){
            this.blNo=sessionStorage.getItem("blNo");
        },
        data(){
            return {
                url:'http://localhost:8888/Cosoproject/',
                money:'',
                blNo:'',
            }
        },
        methods:{
          pay(){
              let _this=this;
              console.log(this.money);
              console.log(this.blNo);
              axios.get(this.url+'alipay',{
                  params:{
                      money:1,
                      blNo:this.blNo
                  }
              }).then((resp)=> {
                  document.querySelector('#alipay').innerHTML = resp.data;
                  document.forms[0].submit();
              });
          }
        }
    }
</script>

<style scoped>

</style>