
angular.module('medekApp.controllers').controller('SeriesController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'SerieService',
function($scope, $rootScope, $stateParams, $location, $modal, SerieService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getSeries = function() {
    	if ($location.path() === '/series/me') {
        	SerieService.userSeries($rootScope.user.id).then(
        	           function(response) {
        	               console.log("Series : "+response.data);
        	               $scope.series = response.data;
        	           }, function(reason) {
        	               alert('FAILED !!!');
        	           });
    	} else {
    		SerieService.serieslist(0, 50, 'id', 'asc').then(
        	           function(response) {
        	               console.log("Series : "+response.data);
        	               $scope.series = response.data;
        	           }, function(reason) {
        	               alert('FAILED !!!');
        	           });
    	}
    };
    
    $scope.series = [ $scope.getSeries() ];
    
    $scope.openSerie = function(id) {
    	var index = 0;
    	if ($location.path().indexOf('/me/') > -1) {
     	   index = 10;
        } else {
     	   index = 11;
        }
    	$location.path($location.path().substring(0,index)+'/view/'+id);
        $location.replace();
    };
    
    $scope.createSerie = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/series/addserie.html',
            controller: 'AddSerieController',
            scope: $scope
          });
    };

} ]);

angular.module('medekApp.controllers').controller('AddSerieController',[
'$scope',
'$rootScope',
'$modalInstance',
'SerieService',
'StoryGenreService',
'SupportService',
'LangService',
function ($scope, $rootScope, $modalInstance, SerieService, StoryGenreService, SupportService, LangService) {
	$scope.serie = { id: null,
			title: '',
			description: '',
			releaseDate: null,
			cover: '',
			support: '',
			supportId: null,
			genre: '',
			genreId: null,
			length: null,
			season: null,
			series: '',
			isSeriesDone: false,
			langs: [],
			subtitles: []
	};

	$scope.supports = [ SupportService.supports()
	                           .then(
		                          function(response) {
		                              console.log("Supports : "+response.data);
		                              $scope.supports = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

	$scope.langs = [ LangService.langs()
	                    .then(
	                          function(response) {
	                              console.log("Langs : "+response.data);
	                              $scope.langs = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

	$scope.genres = [ StoryGenreService.storygenres()
	                    .then(
	                          function(response) {
	                              console.log("Story genres : "+response.data);
	                              $scope.genres = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

	$scope.artists = [ SerieService.artists()
	                           .then(
		                          function(response) {
		                              console.log("Artists : "+response.data);
		                              $scope.artists = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];

	// for date picker
	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.opened = true;
	};

	$scope.saveSerie = function() {
		SerieService.saveSerie($scope.serie).then(
	            function(response) {
	                $rootScope.alerts.push({type: 'success', msg: 'Serie saved !'});
	            	$scope.getSeries();
	            }, function(reason) {
	                $rootScope.alerts.push({type: 'danger', msg: 'Save serie failed'});
	            });
	};

    $scope.ok = function () {
    	$scope.saveSerie();
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);
