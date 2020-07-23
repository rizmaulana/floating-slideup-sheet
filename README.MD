# Floating SlideUp Sheet 🎉

Simple and lightweight UI library for user new experience, combining floating bottom navigation and bottom sheet behaviour. Simple and beautiful.


![alt text](https://github.com/rizmaulana/floating-slideup-sheet/blob/master/assets/slideupmenu.png)

#### Interaction

<img src="https://github.com/rizmaulana/floating-slideup-sheet/blob/master/assets/ezgif.com-resize.gif" width="250" />


[![](https://jitpack.io/v/rizmaulana/floating-slideup-sheet.svg)](https://jitpack.io/#rizmaulana/floating-slideup-sheet)
![GitHub forks](https://img.shields.io/github/forks/rizmaulana/floating-slideup-sheet.svg)
![GitHub contributors](https://img.shields.io/github/contributors/rizmaulana/floating-slideup-sheet.svg)
![GitHub top language](https://img.shields.io/github/languages/top/rizmaulana/floating-slideup-sheet.svg)
![Visitor](https://visitor-badge.laobi.icu/badge?page_id=rizmaulana.floating-slideup-sheet)



## Installation

```bash
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
Add depedency
```bash
dependencies {
	implementation 'com.github.rizmaulana:floating-slideup-sheet:0.1.0'
}
```

## How to use
Add `FloatingSlideUpLayout` to your layout, it will contains two view item for floating menu and expanded panel

```xml
 <id.rizmaulana.floatingslideupsheet.view.FloatingSlideUpLayout
        tools:visibility="invisible"
        android:id="@+id/floating_slideup_sheet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingLeft="16dp"
        android:paddingRight="16dp"
        android:paddingBottom="16dp">
        
        <FrameLayout
           android:id="@+id/container_floating_menu"> 
           ... 
        </FrameLayout>
        
        <LinearLayout
           android:id="@+id/container_expanded_content"> 
           ... 
        </LinearLayout>
</id.rizmaulana.floatingslideupsheet.view.FloatingSlideUpLayout>
```

Setup `FloatingSlideUpPanel` on your view class

```kotlin
 val floatingSlideUpSheet = FloatingSlideUpBuilder(this, floating_slideup_sheet)
            .setFloatingMenuRadiusInDp(32)
            .setFloatingMenuColor(android.R.color.white)
            .setFloatingMenu(container_floating_menu)
            .setPanel(container_expanded_content)
            .build()
```
Now you have awesome floating slideup sheet on your app 🎉

Note : Expandable panel already has `NestedScrollView`, no need add manually, your content will automatically scrollable.


#### Expand and Collapse Expandable Panel Programatically
```kotlin
floatingSlideUpSheet.collapseBottomSheet

floatingSlideUpSheet.expandBottomSheet
```

## Todo

- [ ] Support dark mode
- [ ] Build floating menu from menu item
- [ ] Eliminate code smells
- [ ] Add test






## Contributing
Pull requests are welcome. 


## License
[MIT](https://choosealicense.com/licenses/mit/)