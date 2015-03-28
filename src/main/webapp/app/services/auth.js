angular.module('medekApp.services').service('AuthService', [
'$http', 
'$rootScope', 
'$location',
function($http, $rootScope, $location) {
    this.login = function(login, password) {
        return $http.post('services/auth/login',  {
            login : login,
            password : password
        }).then(
                function(response) {
                    $rootScope.isConnected = true;
                    $rootScope.user = response.data;
                    $http.defaults.headers.common = {'securtoken': response.data.token};
                    $location.path('/home');
                    $location.replace();
                }, function(reason) {
                    $rootScope.authalerts.push({type: 'danger', msg: 'Login failed'});
                    $rootScope.isConnected = false; 
                });
    };

    this.lostpwd = function(mail) {
        return $http.post('services/auth/lostpasswd', {
            mail : mail
        }).then(
                function(response) {
                    alert('OK');
                }, function(reason) {
                    alert('NOK :('); 
                });
    };

    this.logout = function() {
        return $http.post('services/auth/logout', $rootScope.user).then(
                function(response) {
                    $http.defaults.headers.common = {'securtoken': ''};
                    $rootScope.user = {};
                    $rootScope.authalerts.push({type: 'success', msg: 'Logout successful'});
                }, function(reason) {
                    alert('NOK :('); 
                });
    };
} ]);