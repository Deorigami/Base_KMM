//
//  SampleViewModel.swift
//  iosApp
//
//  Created by Ardinata on 28/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//
	
import Foundation
import shared
import os

class SampleIosViewModel : ObservableObject {
    
    private static let logger = Logger(
            subsystem: Bundle.main.bundleIdentifier!,
            category: String(describing: SampleViewModel.self)
        )
    
    let viewModel = SampleViewModel()
    
    @Published var boredApiState : CoreStateData<BoredApiEntity>?
    
    init() {
        viewModel.boredApi.loadData(param: KotlinUnit())
        viewModel.boredApi.state.watch { state in
            self.boredApiState = state
        }
    }
}
