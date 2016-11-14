package io.b1ackr0se.bridddle.injection.components;

import dagger.Component;
import io.b1ackr0se.bridddle.ui.login.DribbbleLoginActivity;
import io.b1ackr0se.bridddle.injection.PerActivity;
import io.b1ackr0se.bridddle.injection.modules.ActivityModule;
import io.b1ackr0se.bridddle.ui.home.HomeFragment;
import io.b1ackr0se.bridddle.ui.profile.ProfileFragment;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(HomeFragment fragment);
    void inject(DribbbleLoginActivity activity);
    void inject(ProfileFragment fragment);
}