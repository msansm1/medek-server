
angular.module('medekApp.controllers').controller('HomeController',[
'$scope',
'$rootScope',
'$modal',
'$location',
'AuthService',
'HomeService',
function($scope, $rootScope, $modal, $location, AuthService, HomeService) {
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
    
    $scope.mycollec = [ HomeService.mycollec().then(
            function(response) {
                console.log("mycollec : "+response.data);
                $scope.mycollec = response.data;
            }, function(reason) {
                alert('FAILED !!!');
            }) ];
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