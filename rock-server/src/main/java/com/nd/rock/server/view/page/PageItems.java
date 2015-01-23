package com.nd.rock.server.view.page;

import java.util.List;

public interface PageItems<E> extends PageBehavor {

	public List<E> getItems();

}
