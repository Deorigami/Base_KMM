import SwiftUI
import shared

struct ContentView: View {
    
    @StateObject var store = SampleIosViewModel()
    
	let greet = Greeting().greet()

	var body: some View {
        Text(store.boredApiState?.data?.activity ?? "")
	}
}
