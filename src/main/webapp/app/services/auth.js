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
                    $http.defaults.headers.common = {'authtoken': response.data.token};
                    $location.path('/projects');
                    $location.replace();
                }, function(reason) {
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
} ]);