import axios from 'axios';

const API_URL = `${process.env.VUE_APP_BASE_URL}/api/auth/`;

class AuthService {
  login(user) {
      console.log("logowanie")
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.token) {
            console.log("dodanie do storage");
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
      console.log("post")
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
    });
  }
}

export default new AuthService();