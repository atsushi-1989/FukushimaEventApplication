package jp.gr.java_conf.atsushitominaga.fukushimaeventapplication

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        val url = bundle?.getString("url")
        val detailId = bundle?.getString("detailId") as String

        if(url != ""){
            detailContener.visibility = View.GONE
            webView.visibility = View.VISIBLE
            webView.webViewClient = object: WebViewClient(){
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressBar.visibility = View.VISIBLE

                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                }
            }

            webView.loadUrl(url)
            return
        }

        //urlが"の時はfragmantを表示
        webView.visibility = View.GONE
        detailContener.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        supportFragmentManager.beginTransaction().add(R.id.detailContener,DetailFragment.newInstance(detailId,"1")).commit()











    }
}
