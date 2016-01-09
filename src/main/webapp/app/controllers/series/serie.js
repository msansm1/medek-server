
angular.module('medekApp.controllers').controller('SerieController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'$upload',
'SerieService',
'StoryGenreService',
'SupportService',
'LangService',
function($scope, $rootScope, $stateParams, $location, $modal, $upload, SerieService, StoryGenreService,
		SupportService, LangService) {
    this.userLogin = $rootScope.user.login;
    
    if (typeof ($stateParams.serieId) != 'undefined') {
	    $scope.serie = [ SerieService.serie($stateParams.serieId)
	                               .then(
			                          function(response) {
			                        	  $('.mainlist').addClass('col-md-4');
			                        	  $('.mainlist').removeClass('col-md-8');
			                              console.log("Serie : "+response.data);
			                              $scope.serie = response.data;
			                              if ($location.path().indexOf('/me/') > -1) {
			                           	   $scope.serie.myview = true;
			                              } else {
			                           	   $scope.serie.myview = false;
			                              }
			                              $('.itempanel').addClass('col-md-6');
			                          }, function(reason) {
			                              alert('FAILED !!!');
			                          }) ];
    } else {
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
    	$('.mainlist').addClass('col-md-4');
    	$('.mainlist').removeClass('col-md-8');
    	$('.itempanel').addClass('col-md-6');
	}

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
    
    $scope.editSerie = function(id) {
    	$location.path('/series/me/edit/'+id);
        $location.replace();
    };
    
    $scope.saveSerie = function() {
    	SerieService.saveSerie($scope.serie).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Serie saved !'});
                	$scope.$parent.getSeries();
                	$location.path('/series/me/edit/'+response.data.id);
                    $location.replace();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save serie failed'});
                });
    };
    
    // cover upload
    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: 'services/tvshows/'+$scope.serie.id+'/coverupload',
            method: 'POST',
            headers: {'securtoken': $rootScope.user.token},
            //withCredentials: true,
            data: {id: $scope.serie.id},
            file: file // or list of files ($files) for html5 only
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total, 10));
          }).success(function(data, status, headers, config) {
            console.log('SUCCESS !! '+data);
          });
        }
    };
    
    $scope.addSerieToMyCollec = function(serie) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/serie/addtocollec.html',
            controller: 'SerieCollecController',
            resolve: {
                serie: function () {
                    return serie;
                }
              }
          });
    };

} ]);

angular.module('medekApp.controllers').controller('SerieCollecController',[
'$scope',
'$rootScope',
'$modalInstance',
'SerieService',
'serie',
function ($scope, $rootScope, $modalInstance, SerieService, serie) {
	$scope.serie = serie;
	$scope.myserie = {
			serieId: serie.id,
			userId: $rootScope.user.id,
			rating: 0,
			comment: ""
	};

    $scope.ok = function () {
    	SerieService.addSerieToCollection($scope.myserie).then (
                function(response) {
                    alert('Add successful');
                    $modalInstance.close('success');
                }, function(reason) {
                    alert('Add failed');
                    $modalInstance.close('fail');
                });
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);