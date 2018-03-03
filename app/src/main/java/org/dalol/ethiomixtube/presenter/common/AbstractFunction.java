package org.dalol.ethiomixtube.presenter.common;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by filippo on 05/11/2017.
 */

public abstract class AbstractFunction<S, I, O> implements Function<I, O> {

    private final S mSubject;

    protected AbstractFunction(S subject) {
        mSubject = subject;
    }

    @Override
    public O apply(@NonNull I i) throws Exception {
        return apply(mSubject, i);
    }

    protected abstract O apply(S subject, I input);
}
