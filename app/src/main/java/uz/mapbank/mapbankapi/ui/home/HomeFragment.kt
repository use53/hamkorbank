package uz.mapbank.mapbankapi.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.astritveliu.boom.Boom
import com.github.premnirmal.textcounter.Formatter
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import me.thanel.swipeactionview.SwipeDirection
import uz.mapbank.mapbankapi.MapsActivity
import uz.mapbank.mapbankapi.R
import uz.mapbank.mapbankapi.adapter.MainAdapter
import uz.mapbank.mapbankapi.adapter.cardadapter.CardAdapter
import uz.mapbank.mapbankapi.common.toast
import uz.mapbank.mapbankapi.databinding.FragmentHomeBinding
import uz.mapbank.mapbankapi.onClick.IonListenerMain
import uz.mapbank.mapbankapi.ui.viewmodel.HomeViewModel
import java.text.NumberFormat
import java.util.*

class HomeFragment : MainBaseFragment(R.layout.fragment_home), IonListenerMain {


    private var homeBinding: FragmentHomeBinding? = null
    private lateinit var cardAdapter: CardAdapter
    private lateinit var mainAdapter: MainAdapter
    private var corrent=false
    private val homeViewModel: HomeViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        homeBinding = binding
        binding.counters.setAutoFormat(false)
        binding.counters.setFormatter { _, suffix, value ->
            NumberFormat.getNumberInstance(Locale.US).format(value)
                .toString() + suffix
        }
        binding.counters.setAutoStart(true)
         Boom(binding.ldMain)


        binding.btUsd.setOnClickListener {
            binding.btUsd.setTextColor(Color.WHITE)
            binding.btUsd.background = resources.getDrawable(
                R.drawable.bt_onclick,
                resources.newTheme()
            )
            binding.btUzs.setTextColor(Color.BLACK)
            binding.btUzs.background = resources.getDrawable(
                R.drawable.bt_color,
                resources.newTheme()
            )
        }

        binding.btUzs.setOnClickListener {
            binding.btUzs.setTextColor(Color.WHITE)
            binding.btUzs.background = resources.getDrawable(
                R.drawable.bt_onclick,
                resources.newTheme()
            )
            binding.btUsd.setTextColor(Color.BLACK)
            binding.btUsd.background = resources.getDrawable(
                R.drawable.bt_color,
                resources.newTheme()
            )
        }
       // binding.ldMain.animateInDirection(SwipeDirection.Right, true)
       /* OverScrollDecoratorHelper.setUpStaticOverScroll(
            binding.ldMain,
            OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL
        );*/
        //Boom(material_text_button as View)

        onCardAdapter()
        onMainAdater()
    }

    private fun onMainAdater() {
        mainAdapter = MainAdapter(this)
        val item = homeViewModel.mainList()
        val layoutManager = GridLayoutManager(requireContext(), 4)
        homeBinding!!.mainRec.layoutManager = layoutManager
        homeBinding!!.mainRec.adapter = mainAdapter
        mainAdapter.submitList(item)


    }

    private fun onCardAdapter() {

        homeViewModel.cardListRead()
        cardAdapter = CardAdapter()
        homeBinding!!.cardRec.adapter = cardAdapter
        homeViewModel.cardHistory.observe(viewLifecycleOwner, {
            cardAdapter.submitList(it)
        })
        itemHelper()
    }

    private fun itemHelper() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        }
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(homeBinding!!.cardRec)
    }

    override fun onClick(pos: Int) {
        when (pos) {
            0 -> {
                navController.navigate(R.id.spinner_nav)
            }
            1 -> {
                navController.navigate(R.id.loan_navigation)
            }
            2 -> {
                navController.navigate(R.id.money_navigation)
            }
            3 -> {
                Intent(requireContext(), MapsActivity::class.java).apply {
                    startActivity(this)
                }
            }
            4 -> {
                navController.navigate(R.id.nav_gallery)
            }
            5 -> {

            }
            6 -> {
                navController.navigate(R.id.exchange_navigation)
            }
            7 -> {
                navController.navigate(R.id.nav_mycard)
            }
        }
    }
}