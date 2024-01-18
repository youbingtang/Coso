<template>
    <div class="container">
        <div id="main1" class="box" style="float: left;margin-left: 10%"></div>
        <div id="main2" class="box" style="margin-left: 900px"></div>
        <div id="main3" class="box"style="float: left;margin-left: 10%;margin-top: 40px"></div>
        <div id="main4" class="box" style="margin-left: 900px;margin-top: 40px"></div>
    </div>
</template>

<script>
    import axios from 'axios'
    let echarts = require('echarts');
    export default {
        name: "Home",
        data(){
          return{
              url:'http://localhost:8888/Cosoproject/',
              data:[],
              data1:[],
              data2:[],
              data3:[],
            }
        },
        mounted() {
            let _this = this;
            axios.get(this.url+'statistics')
                .then(function (data) {
                    _this.data.push(data.data.companycount);
                    _this.data.push(data.data.accountcount);
                    _this.data.push(data.data.issuecount);
                    _this.data.push(data.data.ncompanycount);
                    _this.data.push(data.data.nissueapplycount);

                    _this.data1.push(data.data.issuecount);
                    _this.data1.push(data.data.mnissuecount);
                    _this.data1.push(data.data.toplateformexaminecount);
                    _this.data1.push(data.data.tosignatureexaminecount);
                    _this.data1.push(data.data.topaybillcount);

                    _this.data2.push(data.data.collectioncount);
                    _this.data2.push(data.data.mncollectioncount);
                    _this.data2.push(data.data.toplateformexaminecount);
                    _this.data2.push(data.data.toissueexaminecount);
                    _this.data2.push(data.data.tosignatureexaminecount);

                    _this.data3.push(data.data.signaturecount);
                    _this.data3.push(data.data.mnsignaturecount);
                    _this.data3.push(data.data.tosignaturecount);
                    _this.data3.push(data.data.ocsignaturecount);
                    _this.data3.push(0);

                    _this.initChart();
                })

        },
        methods:{
            initChart() {
                let myChart1 = echarts.init(document.getElementById('main1'));
                let myChart2 = echarts.init(document.getElementById('main2'));
                let myChart3 = echarts.init(document.getElementById('main3'));
                let myChart4 = echarts.init(document.getElementById('main4'));
// 绘制图表
                myChart1.setOption(this.setOption('平台统计数据'));
                myChart2.setOption(this.setOption1('出单统计数据'));
                myChart3.setOption(this.setOption2('收单统计数据'));
                myChart4.setOption(this.setOption3('签章统计数据'));

                myChart1.on('click',(params)=> {
                    if (params.dataIndex===4){
                        this.$router.push({path:'/Index2/blinfotrack'});
                    }else if (params.dataIndex === 3) {
                        this.$router.push({path:'/Index2/companyaudit'});
                    }
                })
            },
            formatter:function(value,index){},
            setOption(title) {
                let option = {
                    title: { text: title },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        type:'category',
                        data: ["平台公司注册总量", "平台用户总量", "平台累计出单总量", "平台本月新增公司总数", "平台本月新增出单申请总数"],
                        axisLabel: { interval: 0,
                            formatter:function(value)
                            {   let ret = "";//拼接加\n返回的类目项
                                let maxLength = 6;//每项显示文字个数
                                let valLength = value.length;//X轴类目项的文字个数
                                let rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                if (rowN > 1)//如果类目项的文字大于3,
                                {
                                    for (let i = 0; i < rowN; i++) {
                                        let temp = "";//每次截取的字符串
                                        let start = i * maxLength;//开始截取的位置
                                        let end = start + maxLength;//结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp = value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                }
                                else {
                                    return value;
                                }
                            }
                        },
                    },
                    yAxis: {},
                    series: [
                        {
                            type: "bar",
                            data:this.data,
                            itemStyle:{
                                normal:{
                                    color:function (params) {
                                        if (params.dataIndex === 4) {
                                            return '#FFA07A'
                                        }else if (params.dataIndex === 3) {
                                            return '#FFA07A'
                                        }else {
                                            return 'blue'
                                        }
                                    }
                                }
                            }
                        },
                    ]
                };
                return option;
            },
            setOption1(title) {
                let option = {
                    title: { text: title },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        type:'category',
                        data: ["累积出单总数", "本月新增出单总数", "待平台审核总数", "待签章审核总数", "待支付提单总数"],
                        axisLabel: { interval: 0,
                            formatter:function(value)
                            {   let ret = "";//拼接加\n返回的类目项
                                let maxLength = 6;//每项显示文字个数
                                let valLength = value.length;//X轴类目项的文字个数
                                let rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                if (rowN > 1)//如果类目项的文字大于3,
                                {
                                    for (let i = 0; i < rowN; i++) {
                                        let temp = "";//每次截取的字符串
                                        let start = i * maxLength;//开始截取的位置
                                        let end = start + maxLength;//结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp = value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                }
                                else {
                                    return value;
                                }
                            }
                        },
                    },
                    yAxis: {},
                    series: [
                        {
                            type: "bar",
                            data: this.data1,
                            itemStyle:{
                                normal:{
                                    color:function (params) {
                                        if (params.dataIndex === 4) {
                                            return 'blue'
                                        }else if (params.dataIndex === 3) {
                                            return 'blue'
                                        }else if (params.dataIndex === 2) {
                                            return 'blue'
                                        }else {
                                            return 'blue'
                                        }
                                    }
                                }
                            }
                        },
                    ]
                };
                return option;
            },
            setOption2(title) {
                let option = {
                    title: { text: title },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        type:'category',
                        data: ["累计收单总数", "本月新增收单总数", "带平台审核提单总数", "待出单审核总数", "待签章审核总数"],
                        axisLabel: { interval: 0,
                            formatter:function(value)
                            {   let ret = "";//拼接加\n返回的类目项
                                let maxLength = 6;//每项显示文字个数
                                let valLength = value.length;//X轴类目项的文字个数
                                let rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                if (rowN > 1)//如果类目项的文字大于3,
                                {
                                    for (let i = 0; i < rowN; i++) {
                                        let temp = "";//每次截取的字符串
                                        let start = i * maxLength;//开始截取的位置
                                        let end = start + maxLength;//结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp = value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                }
                                else {
                                    return value;
                                }
                            }
                        },
                    },
                    yAxis: {},
                    series: [
                        {
                            type: "bar",
                            data: this.data2,
                            itemStyle:{
                                normal:{
                                    color:function (params) {
                                        if (params.dataIndex === 4) {
                                            return 'blue'
                                        }else if (params.dataIndex === 3) {
                                            return 'blue'
                                        }else if (params.dataIndex === 2) {
                                            return 'blue'
                                        }else {
                                            return 'blue'
                                        }
                                    }
                                }
                            }
                        },
                    ]
                };
                return option;
            },
            setOption3(title) {
                let option = {
                    title: { text: title },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    xAxis: {
                        type:'category',
                        data: ["累计签章总数", "本月新增签章总数", "待签章提单总数", "公司签章提单总数", "外司签章提单总数"],
                        axisLabel: { interval: 0,
                            formatter:function(value)
                            {   let ret = "";//拼接加\n返回的类目项
                                let maxLength = 6;//每项显示文字个数
                                let valLength = value.length;//X轴类目项的文字个数
                                let rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                if (rowN > 1)//如果类目项的文字大于3,
                                {
                                    for (let i = 0; i < rowN; i++) {
                                        let temp = "";//每次截取的字符串
                                        let start = i * maxLength;//开始截取的位置
                                        let end = start + maxLength;//结束截取的位置
                                        //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                        temp = value.substring(start, end) + "\n";
                                        ret += temp; //凭借最终的字符串
                                    }
                                    return ret;
                                }
                                else {
                                    return value;
                                }
                            }
                        },
                    },
                    yAxis: {},
                    series: [
                        {
                            type: "bar",
                            data: this.data3,
                            itemStyle:{
                                normal:{
                                    color:function (params) {
                                        if (params.dataIndex === 4) {
                                            return 'blue'
                                        }else if (params.dataIndex === 3) {
                                            return 'blue'
                                        }else if (params.dataIndex === 2) {
                                            return 'blue'
                                        }else {
                                            return 'blue'
                                        }
                                    }
                                }
                            }
                        },
                    ]
                };
                return option;
            },
        }
    }
</script>

<style scoped>
    .container {
        position: relative;
        margin: 0;
        padding: 0;
        justify-content: space-around;
    }
    .box {
        width: 600px;
        height: 400px;
    }
</style>