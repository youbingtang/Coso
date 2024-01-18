//提单详情
<template>
    <center>
        <div>
            <form>
                <table style="width: 1000px">
                    <tr >
                        <td colspan="4" style="width: 50%;height: 20px" >Shipper</td>
                        <td colspan="4">B/L NO.：<input style="width: 80%" type="text" v-model="form.blNo" :disabled="true"></td>
                    </tr>
                    <tr>
                        <td contenteditable="true" colspan="4"  align="left" >
                                <textarea  v-model="form.shipper" :disabled="true">

                                </textarea>
                        </td>
                        <td colspan="4" >
                            <el-image
                                    style="margin-left:20px;width: 92%; height: 50px"
                                    :src="src"></el-image>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 20px">
                            Consignee（The bill is non-negotiable unless marked "To order"or "To the order of…")
                        </td>
                        <td rowspan="6" colspan="4">
                            Received and shipped by the Carrier, in external apparent good order and condition unless otherwise indicated herein . The goods or packages or containers are specified herein from the port of loading (or the place of receipt, if mentioned above) to the port of discharge (or the place of delivery, if mentioned above), being always subject to the terms, rights, defenses, provisions, conditions, exceptions, limitations, and liberties and the merchant's attention is drawn in particular to the Carrier's liberties in respect of on deck stowage and the carrying vessel (methods and route of transportation).
                            The total number of the packages or units stuffed in the container, the description of the goods ,the weights, marks, quality , value and being particulars shown in this bill of lading are furnished by the merchants, and which the carrier has no reasonable means of checking.
                            Merchants hereby expressly accept and agree to be bound by all terms and conditions stated herein whether printed, written, stamped or incorporated on the face or reverse side hereof, as fully as if they were all signed by the merchant.
                            IN WITNESS WHEREOF the number of original bills of lading all of this tenor and date stated below have been signed, one of which being surrendered the others to be void.
                        </td>
                    </tr>
                    <tr>
                        <td contenteditable="true" colspan="4" style="height: 40px"><textarea v-model="form.consignee" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 20px">(*数字格式)For delivery of the goods, pls apply to :</td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 40px"><textarea v-model="form.proId" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 20px">Notify Party (No liability shall attach to the Carrier or to his Agent for failure to notify)  </td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 40px"><textarea v-model="form.notifyParty" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="width: 25%">Place of Receipt (Multimodal Transport only)</td>
                        <td colspan="2">Pre-carriage by</td>
                        <td colspan="2">Type of Movement</td>
                        <td colspan="2">No. of Original B(s)/L</td>
                    </tr>
                    <tr style="height: 40px">
                        <td colspan="2" ><textarea v-model="form.placeOfreceipt" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.preCarriageBy" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.typeOfMovement" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.NoOfOriginal" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="height: 20px">Vessel Name</td>
                        <td colspan="2">Voyage No.</td>
                        <td colspan="2">(*数字格式)Port of Loading </td>
                        <td colspan="2">Port of Transshipment</td>
                    </tr>
                    <tr style="height: 40px">
                        <td colspan="2" ><textarea v-model="form.vessel" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.voyageNo" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.portOfLoading" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.portOfTransshipment" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2">Port of Discharge  </td>
                        <td colspan="2">Place of Delivery(Multimodal Transport only)</td>
                        <td colspan="4">Final destination * for reference only</td>
                    </tr>
                    <tr>
                        <td colspan="2" style="height: 40px"><textarea v-model="form.PortOfDischarge" :disabled="true"></textarea></td>
                        <td colspan="2"><textarea v-model="form.PlaceOfDelivery" :disabled="true"></textarea></td>
                        <td colspan="4"><textarea v-model="form.finalDestination" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2">Marks & Nos. Container /Seal No.</td>
                        <td colspan="2">No. of Container or Packages</td>
                        <td colspan="2">Description of Goods</td>
                        <td>Gross Weight</td>
                        <td>Measurement</td>
                    </tr>
                    <tr>
                        <td colspan="8" style="height: 200px"><textarea readonly></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 40px">Freight & Charges,   Prepaid   Collect<br>
                            Rates, Weight and /or Measurement subject to correction</td>
                        <td colspan="4" style="height: 40px">Declared Cargo Value
                            If Merchant enters a value<br>Carrier's per Package limitation of liability shall not apply and the valorem rate will be charged.</td>
                    </tr>
                    <tr>
                        <td style="height: 100px" colspan="4"><textarea v-model="form.freightCharges" readonly></textarea></td>
                        <td  colspan="4"><textarea v-model="form.declared" readonly></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4">Place and date of issue</td>
                        <td rowspan="4" colspan="4"><textarea v-model="form.placeAndDate" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td style="height: 100px" colspan="4"><textarea readonly></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4">Total Number of containers or packages (in Words):</td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 100px" ><textarea v-model="form.totalNumber" :disabled="true"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="8" style="height: 70px" align="center">Subject to the Terms and Conditions under cosco shipping lines co.,ltd or by  QR code</td>
                    </tr>
                </table>
                <el-select v-model="form.sendCompanyId" placeholder="请选择出单公司" :disabled="true">
                    <el-option
                            v-for="item in sendCompanyList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id"
                    >
                    </el-option>
                </el-select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <el-select v-model="form.signatureCompanyId" placeholder="请选择签章公司" :disabled="true">
                    <el-option
                            v-for="item in signCompanyList"
                            :key="item.id"
                            :value="item.id"
                            :label="item.name">
                    </el-option>
                </el-select>
            </form><br/>
            <el-button type="primary" @click="back">返回</el-button>
        </div>
    </center>

</template>

<script>
    import axios from 'axios'
    export default {
        name: "Recording",
        data(){
            return{
                url: "http://localhost:8888/Cosoproject/",
                tupian:'',
                cid:'',
                file:'',
                src:'',
                sendCompanyList:[],
                signCompanyList:[],
                form:{
                }
            }
        },
        created(){
            let blNo=this.$route.query.edit;
            this.tupian = this.$route.query.tupian;
            let _this=this;
            _this.findlogo();
            axios.get(this.url+'findrecordingbyblNoo',{
                params:{
                    blNo:blNo
                }
            }).then(function (data) {
                _this.form=data.data;
            })
        },
        methods: {
            back() {
                this.$router.back();
            },
            findlogo(){
                let _this=this;
                axios.get(this.url+'selectCompanyLogo',{
                    params:{
                        cid:this.tupian
                    }
                }).then(function (data) {
                    _this.file=data.data.logofile;
                    console.log(data);
                    _this.src="http://localhost:8888/logo/"+_this.file;
                })

            }

        }
    }
</script>

<style scoped>
    table,table tr th, table tr td {



    }

    table {
        border-collapse: collapse;
    }


    table, th, td {
        position: relative;
        border: 1px solid black;
    }

    table{

        width: 400px;

        border-collapse: collapse;

    }
    .color{
        background-color: #00A5FF;
    }

    .res{
        width: 1468px;
    }

    textarea {
        top: 0px !important;
        left: 0px !important;
        width: 100%;
        height: 100%;
        position: absolute;
        resize: none;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        border: solid;
        border-width: 4px;
        border-color: white;
        margin:0px;
        padding:0px;
    }
</style>