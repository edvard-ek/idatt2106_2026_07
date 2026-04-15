<template>
  <div class="group relative mx-auto w-full max-w-[31rem]">
    <div
      class="absolute inset-0 rounded-[2.25rem] bg-[#4158a6]/10 blur-3xl transition duration-500 group-hover:bg-[#ff8343]/16"
      aria-hidden="true"
    ></div>

    <div
      class="relative overflow-hidden rounded-[2.25rem] border border-black/8 bg-[linear-gradient(180deg,#fff6eb_0%,#fffdf7_100%)] px-6 pb-6 pt-5 shadow-[0_24px_70px_rgba(34,34,40,0.12)] sm:px-8 sm:pb-8 sm:pt-7"
    >
      <div class="flex items-start justify-between gap-4">
        <div>
          <p class="text-[0.72rem] font-black tracking-[0.16em] text-[#4158a6] uppercase">
            Hold musepeker for å undersøke.
          </p>
          <p class="mt-2 max-w-[16rem] text-sm leading-6 text-[#5f606b]">
            Hvem er Biggur?
          </p>
        </div>
        <div class="rounded-full bg-white px-3 py-2 text-[0.72rem] font-black tracking-[0.12em] uppercase">
          Case 01
        </div>
      </div>

      <div
        class="relative mt-6 overflow-hidden rounded-[1.75rem] bg-[linear-gradient(180deg,#f4e5d2_0%,#fff7ee_100%)] px-4 py-6 sm:px-6"
      >
        <div
          class="pointer-events-none absolute inset-x-0 top-0 h-24 bg-[linear-gradient(90deg,#ff8343_0%,#f1dec6_35%,#179bae_70%,#4158a6_100%)] opacity-90"
          aria-hidden="true"
        ></div>
        <div
          class="pointer-events-none absolute -right-10 bottom-2 h-40 w-40 rounded-full bg-[#ff8343]/16 blur-2xl"
          aria-hidden="true"
        ></div>

        <div class="relative mx-auto w-full max-w-[23rem]">
          <svg
            viewBox="0 0 879 1166"
            class="w-full transition duration-500 group-hover:-translate-y-1"
            role="img"
            aria-label="Biggur som får på seg detektivhatt og forstørrelsesglass ved hover"
          >
            <g>
              <path
                d="M502 387C669.342 387 805 510.569 805 663C805 752.805 757.912 832.591 685 882.993V1076C685 1125.71 644.706 1166 595 1166H289C239.294 1166 199 1125.71 199 1076V624.945H201.858C222.21 490.523 348.832 387 502 387Z"
                fill="#839FFF"
              />
              <path
                d="M685 642C685 682.317 652.317 715 612 715C571.683 715 539 682.317 539 642C539 601.683 571.683 569 612 569C652.317 569 685 601.683 685 642Z"
                fill="#FFFAF0"
              />
              <circle cx="421" cy="642" r="73" fill="#FFFAF0" />
              <circle cx="617.5" cy="649.5" r="20.5" fill="#2C2C2C" />
              <circle cx="428.5" cy="649.5" r="20.5" fill="#2C2C2C" />
              <mask id="biggur-mask-right" style="mask-type: alpha" maskUnits="userSpaceOnUse" x="539" y="569" width="146" height="146">
                <path
                  d="M685 642C685 682.317 652.317 715 612 715C571.683 715 539 682.317 539 642C539 601.683 571.683 569 612 569C652.317 569 685 601.683 685 642Z"
                  fill="#FFFAF0"
                />
              </mask>
              <g mask="url(#biggur-mask-right)">
                <rect x="539" y="569" width="146" height="73" fill="#B4C5FF" />
              </g>
              <mask id="biggur-mask-left" style="mask-type: alpha" maskUnits="userSpaceOnUse" x="348" y="569" width="146" height="146">
                <circle cx="421" cy="642" r="73" fill="#FFFAF0" />
              </mask>
              <g mask="url(#biggur-mask-left)">
                <rect x="348" y="569" width="146" height="73" fill="#B4C5FF" />
              </g>
              <path
                d="M878 960V1076C878 1125.71 837.706 1166 788 1166H525H199C149.294 1166 109 1125.71 109 1076V960C109 910.294 149.294 870 199 870H391L525 1166L646.5 870H788C837.706 870 878 910.294 878 960Z"
                fill="#C49C69"
              />
              <path
                d="M799.787 891.213L525 1166L250.213 891.213C231.314 872.314 244.699 840 271.426 840H368.589C409.324 840 444.98 867.36 455.522 906.706L525 1166L594.478 906.706C605.02 867.36 640.676 840 681.411 840H778.574C805.301 840 818.686 872.314 799.787 891.213Z"
                fill="#DFC6A6"
              />
            </g>

            <g
              class="origin-center -translate-y-10 scale-95 opacity-0 transition-all duration-500 ease-out group-hover:translate-y-0 group-hover:scale-100 group-hover:opacity-100"
            >
              <path
                d="M833.872 332.589C892.766 475.135 70.2197 709.291 19.3802 550.176C1.41553 493.951 56.8709 453.268 182.183 403.586C182.183 403.586 165.54 163.315 271.505 141.169C334.178 128.07 362.777 190.574 426.712 193.998C498.888 197.864 556.234 94.1414 605.69 146.852C643.378 187.019 640.26 279.308 640.26 279.308C755.81 268.476 815.455 288.011 833.872 332.589Z"
                fill="#2C2C2C"
              />
            </g>

            <g
              class="origin-center scale-75 opacity-0 transition-all delay-100 duration-500 ease-out group-hover:scale-100 group-hover:opacity-100"
            >
              <circle cx="421" cy="642" r="94" fill="none" stroke="#2C2C2C" stroke-width="18" />
              <circle cx="612" cy="642" r="94" fill="none" stroke="#2C2C2C" stroke-width="18" />
              <path
                d="M514 642H519"
                stroke="#2C2C2C"
                stroke-width="18"
                stroke-linecap="round"
              />
              <path
                d="M700 720L770 786"
                stroke="#2C2C2C"
                stroke-width="18"
                stroke-linecap="round"
              />
              <circle cx="421" cy="642" r="12" fill="#FFFFFF" opacity="0.42" />
              <circle cx="612" cy="642" r="12" fill="#FFFFFF" opacity="0.42" />
            </g>
          </svg>
        </div>
      </div>


    </div>
  </div>
</template>
