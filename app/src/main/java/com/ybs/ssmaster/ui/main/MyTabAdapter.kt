package com.ybs.ssmaster.ui.main

import android.view.Gravity
import com.ybs.reslib.widgets.verticaltablayout.adapter.TabAdapter
import com.ybs.reslib.widgets.verticaltablayout.widget.ITabView
import com.ybs.ssmaster.R

/**
 *
 * @ProjectName: ServiceRequestApp
 * @Package: com.ybs.ssmaster.ui.main
 * @Description: java类作用描述
 * @Author: Zzb
 * @CreateDate: 2021/5/25 16:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/25 16:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyTabAdapter : TabAdapter {


    private val titles = mutableListOf("店长推荐", "餐厅服务", "菜品进度")
    private val iconsUnSelected  = mutableListOf(
        R.mipmap.icon_tuijian_click
        ,R.mipmap.icon_serve_click
        ,R.mipmap.icon_jindu_click)

    private val iconsSelected = mutableListOf(
        R.mipmap.icon_tuijian
        ,R.mipmap.icon_serve
        ,R.mipmap.icon_jindu)

    private val tabTitles = mutableListOf<ITabView.TabTitle>()
    private val tabIcons = mutableListOf<ITabView.TabIcon>()

    init {

        titles.forEachIndexed { index, s ->

            tabIcons.add(
                ITabView
                    .TabIcon
                    .Builder()
                    .setIconGravity(Gravity.TOP)
                    .setIcon(iconsSelected[index], iconsUnSelected[index])
                    .build()
            )

            tabTitles.add(
                ITabView
                    .TabTitle
                    .Builder()
                    .setContent(s)
                    .setTextSize(28)
                    .setTextColor(
                        com.ybs.reslib.R.color.tabSelected, com.ybs.reslib.R.color.tabUnSelected
                    )
                    .build()
            )
        }


    }


    override fun getCount() = titles.size

    override fun getBadge(position: Int) = null

    override fun getIcon(position: Int)=tabIcons[position]

    override fun getTitle(position: Int) = tabTitles[position]

    override fun getBackground(position: Int) = 0
}