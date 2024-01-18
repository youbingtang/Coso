<template>
    <div id="data-view">
        <dv-full-screen-container style="background-color: #03233D">
            <dv-border-box-11 title="中远海云提单数据展示">
                <div id="topdiv">

                    <dv-decoration-12  style="width:150px;height:150px;float:left;padding-top: 10px" />


                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                         注册公司总量(家)
                        </span>
                                <br/><br/>
                                <span  class="fint">
                           {{shujulist[0]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                     用户总量(人)
                        </span>
                                <br/><br/>
                                <span  class="finr">
                             {{shujulist[1]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                           累计出单总量(单)
                        </span>
                                <br/><br/>
                                <span class="fint">
                            {{shujulist[2]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                            待审核提单(单)
                        </span>
                                <br/><br/>
                                <span  class="finr">
                           {{shujulist[3]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                           待付款提单(单)
                        </span>
                                <br/><br/>
                                <span class="fint">
                            {{shujulist[4]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                           本司签章总数(单)
                        </span>
                                <br/><br/>
                                <span class="finr">
                          {{shujulist[5]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                          待签章核审数(单)
                        </span>
                                <br/><br/>
                                <span  class="fint">
                        {{shujulist[6]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                          本司提单总数(单)
                        </span>
                                <br/><br/>
                                <span  class="finr">
                            {{shujulist[7]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>

                    <div style="width: 150px; height: 150px; background-color: #03233D;color:white;float:left;margin-left: 40px;text-align: center;padding-top: 17px">
                        <dv-border-box-10>
                            <div style="padding-top: 30px">
                                 <span>
                          本月提单总数(单)
                        </span>
                                <br/><br/>
                                <span class="fint">
                      {{shujulist[8]}}
                        </span>
                            </div>
                        </dv-border-box-10>
                    </div>


                </div>

                <dv-decoration-10 style="width:99%;height:5px; padding-top: 20px" />



                <!-- <top-header :shujulist="shujulist"/>-->

                <div style="width: auto;height: auto;float: left">
                    <!-- <left-context :map="map"/>-->
                    <div style="padding-top: 20px">
                        <div style="padding-top: 15px;padding-left: 20px">
                            <dv-scroll-board :config="config" style="width:650px;height:350px" />
                        </div>
                        <div style="padding-top: 20px;padding-left: 20px">
                            <dv-scroll-board :config="twoconfig" style="width:650px;height:350px" />
                        </div>
                    </div>


                </div>
                <div style="float: left; width: 700px;padding-top: 20px;padding-left: 20px;
        height: 740px;">
                    <dv-border-box-8 :reverse="true">

                        <div style="padding-top: 5px;padding-left: 5px">
                            <!--class = map-->
                            <baidu-map class="map" :center="centermap" :zoom="mapzoom" @ready="handler">

                                <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
                                <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" 				:autoLocation="true"></bm-geolocation>

                                <template v-for="(item,index) in map">
                                    <bm-marker :position="item.center"
                                               :key="index" :dragging="item.dragging"
                                               @click="look(item)"
                                               animation="BMAP_ANIMATION_DROP">
                                    </bm-marker>
                                </template>

                                <bm-info-window :position="position"
                                                :show="show"
                                                @close="infoWindowClose()"
                                                @open="infoWindowOpen()">{{from.addres}}</bm-info-window>
                            </baidu-map>
                        </div>
                        <!--   <feixianmap/>-->
                    </dv-border-box-8>
                </div>
                <div style="padding-top: 20px;float: left;width: 450px;height: 740px;padding-left: 20px">
                    <dv-border-box-13 :reverse="true">

                        <div>
                            <div style="padding-left: 120px;padding-top: 50px">
                                <span style="font-size: 20px;color: goldenrod;">提单总数同比上月增长</span>
                            </div>

                            <br/><br/>
                            <div style="padding-left: 150px;padding-top: 30px">
                                <dv-decoration-9 style="width:150px;height:150px;">{{result1}}</dv-decoration-9>
                            </div>
                            <br/>
                            <dv-decoration-5 style="width:400px;height:40px;padding-left: 15px" />
                            <br/>
                            <div>
                                <div style="padding-top: 20px;padding-left: 80px">
                                    <dv-decoration-7 style="width:300px;height:30px;"><p style="color: cadetblue;font-size: 20px">月提单总数对比(半年)</p></dv-decoration-7>
                                </div>
                                <div style="padding-top: 40px;padding-left: 30px">
                                    <dv-capsule-chart :config="threeconfig" style="width:400px;height:270px" />
                                </div>
                            </div>
                        </div>
                        <!--  <right-context/>-->
                    </dv-border-box-13>
                </div>
            </dv-border-box-11>
        </dv-full-screen-container>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        name: 'Home',
        data(){
            return {
                result1:"",
                threeconfig:{
                    data: [
                        {
                            name: '一月',
                            value: 60
                        },
                        {
                            name: '二月',
                            value: 167
                        },
                        {
                            name: '三月',
                            value: 123
                        },
                        {
                            name: '四月',
                            value: 98
                        },
                        {
                            name: '五月',
                            value: 75
                        },
                        {
                            name: '六月',
                            value: 66
                        },
                    ],
                    colors: ['#e062ae', '#fb7293', '#e690d1', '#32c5e9', '#96bfff'],
                    unit: '(单)',
                    showValue: true
                },
                value:'',
                position:{},
                show: false,
                from:{},
                centermap:{
                    lng: 121.4472540000, lat: 31.3236200000
                },
                mapzoom:15,
                map:[{
                    center: {lng: 121.4472540000, lat: 31.3236200000},
                    zoom: 15,
                    show: true,
                    dragging: false,
                    addres:'旅顺港'

                },{
                    center: {lng: 121.4472540000, lat: 32.3236200000},
                    zoom: 15,
                    show: true,
                    dragging: true,
                    addres:'上海港'
                },{
                    center: {lng: 121.4472540000, lat: 35.3236200000},
                    zoom: 15,
                    show: true,
                    dragging: true,
                    addres:'鲅鱼圈'
                }],
                list10:[],
                list11:[],
                shujulist:[],
                config:{
                    //服务开通申请
                    header: ['企业名称', '申请开通服务', '申请日期'],
                    data: [
                        ['暂无数据','暂无数据', '暂无数据'],
                        ['暂无数据', '暂无数据', '暂无数据'],
                        ['暂无数据','暂无数据', '暂无数据'],
                        ['暂无数据', '暂无数据', '暂无数据'],
                        ['暂无数据','暂无数据', '暂无数据'],
                        ['暂无数据', '暂无数据', '暂无数据'],
                        ['暂无数据','暂无数据', '暂无数据'],
                        ['暂无数据', '暂无数据', '暂无数据'],
                        ['暂无数据','暂无数据', '暂无数据'],
                        ['暂无数据', '暂无数据', '暂无数据'],
                    ],
                    index: true,
                    columnWidth: [50],
                    align: ['center']
                },
                twoconfig:{
                    //出单申请
                    header: ['企业名称', '提单编号', '申请日期'],
                    data: [
                        ['河南众立赢机电设备有限公司','B/L-No.xxxxxxxxxxxxxxxxxxxxxxxx', 1625068800000],
                        ['行2列1', '行2列2', '行2列3'],
                        ['行3列1', '行3列2', '行3列3'],
                        ['行4列1', '行4列2', '行4列3'],
                        ['行5列1', '行5列2', '行5列3'],
                        ['行6列1', '行6列2', '行6列3'],
                        ['行7列1', '行7列2', '行7列3'],
                        ['行8列1', '行8列2', '行8列3'],
                        ['行9列1', '行9列2', '行9列3'],
                        ['行10列1', '行10列2', '行10列3']
                    ],
                    index: true,
                    columnWidth: [50],
                    align: ['center']
                }

            }
        },
        created(){
            let  _this = this;
            axios.get('http://localhost:8888/Cosoproject/osbillCompany')
                .then(resp=>{
                    console.log(resp.data);
                    _this.shujulist.push(resp.data.list1,resp.data.list2,resp.data.list3,resp.data.list4,resp.data.list5,resp.data.list6,
                        resp.data.list7,resp.data.list8,resp.data.list9);
                    _this.list10 = resp.data.list10;
                    _this.list11 = resp.data.list11;
                    console.log(this.list10);
                    console.log(this.list11[0]);//提单编号
                    _this.twoconfig.data = [
                        [this.list11[0].name,this.list11[0].blNO,this.list11[0].sendBillDate],
                        [this.list11[1].name,this.list11[1].blNO,this.list11[1].sendBillDate],
                        [this.list11[2].name,this.list11[2].blNO,this.list11[2].sendBillDate],
                        [this.list11[3].name,this.list11[3].blNO,this.list11[3].sendBillDate],
                        [this.list11[4].name,this.list11[4].blNO,this.list11[4].sendBillDate],
                        [this.list11[5].name,this.list11[5].blNO,this.list11[5].sendBillDate],
                        [this.list11[6].name,this.list11[6].blNO,this.list11[6].sendBillDate],
                        [this.list11[7].name,this.list11[7].blNO,this.list11[7].sendBillDate],
                        [this.list11[8].name,this.list11[8].blNO,this.list11[8].sendBillDate],
                        [this.list11[9].name,this.list11[9].blNO,this.list11[9].sendBillDate]
                    ];
                    if (resp.data.list10.size === 10) {
                    _this.config.data = [
                        [this.list10[0].name,'出单服务',this.list10[0].applyDate],
                        [this.list10[1].name,'出单服务',this.list10[1].applyDate],
                        [this.list10[2].name,'出单服务',this.list10[2].applyDate],
                        [this.list10[3].name,'出单服务',this.list10[3].applyDate],
                        [this.list10[4].name,'出单服务',this.list10[4].applyDate],
                        [this.list10[5].name,'出单服务',this.list10[5].applyDate],
                        [this.list10[6].name,'出单服务',this.list10[6].applyDate],
                        [this.list10[7].name,'出单服务',this.list10[7].applyDate],
                        [this.list10[8].name,'出单服务',this.list10[8].applyDate],
                        [this.list10[9].name,'出单服务',this.list10[9].applyDate]
                    ]}

                        _this.twoconfig={..._this.twoconfig};
                    _this.result1=resp.data.result1;
                    _this.threeconfig.data[0].value = resp.data.result2;
                    _this.threeconfig.data[1].value = resp.data.result3;
                    _this.threeconfig.data[2].value = resp.data.result4;
                    _this.threeconfig.data[3].value = resp.data.result5;
                    _this.threeconfig.data[4].value = resp.data.result6;
                    _this.threeconfig.data[5].value = resp.data.result7;
                    console.log(resp.data.result7);


                    _this.threeconfig={..._this.threeconfig};
                    _this.config={..._this.config};
                })


        },
        methods: {
            gundong(){
                for (let item of this.map){
                    if (item.addres === this.value){
                        this.centermap = item.center;
                        return;
                    }
                }
            },
            infoWindowClose() {
                this.show = false;
            },
            infoWindowOpen() {
                this.show = true;
            },
            //防止弹窗数据出现只有一个 使用此方法
            //将需要弹窗的数据每一个拿出来
            look(item) {
                this.from = item;
                console.log(item.center);
                this.position = item.center;
                this.show = true;
            },
            //点击控制台打印经纬度
            handler ({BMap, map}) {
                let mapStyle={style:"midnight"}
                map.setMapStyle(mapStyle);
                let me = this;
                console.log(BMap, map)
                // 鼠标缩放
                map.enableScrollWheelZoom(true);
                // 点击事件获取经纬度
                map.addEventListener('click', function (e) {
                    console.log(e.point.lng, e.point.lat)
                })
            }
        }

    }
</script>
<style>
    #data-view {
        width: 100%;
        height: 100%;
        background-color: #030409;
        color: #fff;
    }
    .fint{
        padding-top: 30px;
        font-size: 45px;
        color: coral;
    }
    .finr{
        font-size: 45px;
        color:#0F9EAE;
    }
    #topdiv{

        padding-top: 80px;
        padding-left: 18px;
    }
    .map {
        width: 690px;
        height: 730px;
    }

</style>
