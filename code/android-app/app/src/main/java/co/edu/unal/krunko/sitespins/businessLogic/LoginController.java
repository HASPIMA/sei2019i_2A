package co.edu.unal.krunko.sitespins.businessLogic;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import co.edu.unal.krunko.sitespins.dataAccess.models.User;
import co.edu.unal.krunko.sitespins.dataAccess.repositories.UserRepository;

public class LoginController {

	private Activity activity;



	public LoginController(Activity activity) {
		this.activity = activity;
	}
	public LoginController() {}

	public enum LoginStatus {
		/**
		 * Here we show the auth state while logging in
		 * <p>
		 * WRONG CREDENTIALS: the email or the password are/is invalid.
		 * SUCCESSFUL_LOGIN: the user logging in was successful.
		 * EMAIL_IS_REQUIRED_OR_INVALID: if the user has enter an invalid email.
		 * PASSWORD_IS_REQUIRED: if the password has a length lower than 8 characters.
		 */
		WRONG_CREDENTIALS,
		SUCCESSFUL_LOGIN,
		EMAIL_IS_REQUIRED_OR_INVALID,
		PASSWORD_IS_REQUIRED
	}

	public LoginStatus loginWithEmailAndPassword(String email, String password) {
		if (email == null
				|| !email.toLowerCase().matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")) {
			return LoginStatus.EMAIL_IS_REQUIRED_OR_INVALID;
		} else if (password == null || password.length() < 8) {
			return LoginStatus.PASSWORD_IS_REQUIRED;
		}

		UserRepository userRepository = new UserRepository(this.activity);


		return userRepository.getUserByEmailAndPassword(email, password) != null ? LoginStatus.SUCCESSFUL_LOGIN : LoginStatus.WRONG_CREDENTIALS;
	}

	public boolean isLoggedIn(){
		return new UserRepository(null).getUser() != null;
	}

	public void handleFacebookAccessToken(AccessToken token) {
		UserRepository userRepository = new UserRepository(this.activity);
		if(userRepository.getFacebookUser(token) != null){
			Toast.makeText(this.activity, "Authentication success.",
					Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this.activity, "Authentication failed.",
					Toast.LENGTH_SHORT).show();
		}
	}

	public CallbackManager getCallbackManager(){
		return new UserRepository(this.activity).getmCallbackManager();
	}
}
