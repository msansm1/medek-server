
angular.module('medekApp.controllers').controller('HomeController',[
'$scope',
'$rootScope',
'$modal',
'$location',
'AuthService',
function($scope, $rootScope, $modal, $location, AuthService) {
    this.userLogin = $rootScope.user.login;
    $rootScope.alerts = [];

    $rootScope.logout = function () {
        AuthService.logout();
        $location.path('/');
        $location.replace();
    };
    
    $rootScope.openProfile = function () {
        var modalInstance = $modal.open({
          templateUrl: 'app/views/profile.html',
          controller: 'ProfileCtrl'
        });
    };
    
    $rootScope.closeAlert = function(index) {
        $rootScope.alerts.splice(index, 1);
    };
    
    $rootScope.isActive = function (viewLocation) {
        return $location.path().indexOf(viewLocation) === 0;
    };
} ]);

angular.module('medekApp.controllers').controller('ProfileCtrl',[
'$scope',
'$modalInstance',
'UserService',
function ($scope, $modalInstance, UserService) {

    $scope.ok = function () {
        UserService.updateProfile();
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
  }]);