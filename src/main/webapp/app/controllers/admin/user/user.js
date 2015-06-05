angular.module('medekApp.controllers').controller('UserController',[
'$scope',
'$rootScope',
'$stateParams',
'$modal',
'$location',
'UserService',
function($scope, $rootScope, $stateParams, $modal, $location, UserService) {
    this.userLogin = $rootScope.user.login;

    if (typeof ($stateParams.userId) != 'undefined') {
    	$scope.user = [ UserService.user($stateParams.userId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("User : "+response.data);
		                              $scope.user = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    } else {
    	$scope.user = { id: null,
    					login: '',
    					email: '',
    					password: '',
    					confpassword: ''
    	};
  	    $('.mainlist').addClass('col-md-4');
	    $('.mainlist').removeClass('col-md-8');
        $('.itempanel').addClass('col-md-6');
    }
    
    $scope.saveUser = function() {
    	UserService.saveUser($scope.user).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'User saved !'});
                	$scope.$parent.getUsers();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save user failed'});
                });
    };

} ]);
