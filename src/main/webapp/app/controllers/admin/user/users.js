
angular.module('medekApp.controllers').controller('UsersController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'UserService',
function($scope, $rootScope, $stateParams, $location, UserService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getUsers = function () {
    	UserService.getAllUsers()
        .then(
           function(response) {
               console.log("Users : "+response.data);
               $scope.users = response.data;
           }, function(reason) {
               alert('FAILED !!!');
           });
    };
    
    $scope.users = [ $scope.getUsers() ];
    
    $scope.openUser = function(id) {
    	$location.path('/admin/users/'+id);
        $location.replace();
    };

} ]);