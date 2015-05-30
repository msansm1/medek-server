
angular.module('medekApp.controllers').controller('SeriesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'SerieService',
function($scope, $rootScope, $stateParams, $location, SerieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getSeries = function() {
    	SerieService.series().then(
           function(response) {
               console.log("Series : "+response.data);
               $scope.series = response.data;
           }, function(reason) {
               alert('FAILED !!!');
           });
    };
    
    $scope.series = [ $scope.getSeries() ];
    
    $scope.openSerie = function(id) {
    	$location.path('/series/'+id);
        $location.replace();
    };

} ]);