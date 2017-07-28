# NavigationDrawerLibrary
A library to create navigation drawer items with image, with subitems and customize toolbar easily

# How To Use

- Extend BaseNavigationDrawerActivity (You can use layout xml file activity_base_navigation_drawer for navigation drawer in your activity)
- Extend BaseNavDrawerAdapter (If your menu items have sublists, override getSubListItemView method and implement the view in this method
- Init Navigation Drawer items in initNavigationDrawer method
- While creating subitems, extend AbstractSubItemModel class and customize your model class.
- Handle drawer item's click events in onNavigationItemSelected method
- Init your customized Toolbar in initToolbar method
- Set Toolbar hamburger menu icon color in manifest using styles(AppTheme.FullScreen.WhiteArrow or AppTheme.FullScreen.BlackArrow) for Activity theme


