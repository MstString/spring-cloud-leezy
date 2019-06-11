package org.spring.hibernate.api;

public interface UserAPI {

    String PREFIX = "/user";

    String FIND_BY_ID = PREFIX + "/find_by_id";

    String SAVE = PREFIX + "/save";

    String DELETE = PREFIX + "/delete";

    String LISTALL = PREFIX + "/list";

    String REMOTE_PREFIX = "/rest";

    String REMOTE_FIND_BY_ID = REMOTE_PREFIX + "/find_by_id";

    String REMOTE_SAVE = REMOTE_PREFIX + "/save";
}
