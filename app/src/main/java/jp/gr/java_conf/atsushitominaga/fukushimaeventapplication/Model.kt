package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

class Model() {
    var tagCollor : Int = 0
    var imageUrl : String = ""
    var titleText : String = ""
    var dateText : String = ""
    var placeText : String = ""
    var url : String =""
    constructor(tagCollor : Int, imageUrl : String, titleText : String, dateText : String, placeText : String, url : String):this(){
        this.tagCollor = tagCollor
        this.imageUrl = imageUrl
        this.titleText = titleText
        this.dateText = dateText
        this.placeText = placeText
        this.url = url
    }

}