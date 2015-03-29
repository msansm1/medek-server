angular.module('medekApp.services').service('UserService', [
'$http', 
'$rootScope', 
function($http, $rootScope) {
    this.updateProfile = function() {
        return $http.post('services/users/profile', $rootScope.user);
    };
    
    this.getAllUsers = function() {
        return $http.post('services/admin/users/');
    };
    
} ]);