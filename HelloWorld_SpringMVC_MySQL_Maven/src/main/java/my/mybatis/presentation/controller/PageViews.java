package my.mybatis.presentation.controller;

/**
 * Enumeration of all views (concerning MVC) used in the current package.
 */
enum PageViews {
	
	LIST("list-users"), NEW("new-user"), EDIT("edit-user");

	private String viewName;

	private PageViews(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return this.viewName;
	}
}
