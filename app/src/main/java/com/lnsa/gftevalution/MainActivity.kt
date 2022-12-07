package com.lnsa.gftevalution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.lnsa.gftevalution.components.InboxListOfNews
import com.lnsa.gftevalution.models.InboxItemModel
import com.lnsa.gftevalution.models.InboxItemModelDataTypes
import com.lnsa.gftevalution.models.PendenciesData
import com.lnsa.gftevalution.utils.InboxDataUtils
import com.lnsa.gftevalution.utils.InboxProvider
import com.lnsa.gftevalution.viewmodel.InboxDialogViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var listOfNews: InboxListOfNews
    private val viewModel: InboxDialogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOfNews = findViewById<InboxListOfNews>(R.id.inbox_home_list_news)

        mockApiCall()
    }

    private fun mockApiCall() {
        viewModel.separateData(
            InboxItemModelDataTypes(
                InboxProvider.getInboxModel(),
                InboxProvider.getPendencies()
            )
        )
        listOfNews.setData()
        InboxDataUtils.ref.sortAll()
    }
}